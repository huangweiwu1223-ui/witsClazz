package com.example1.demo1.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

/**
 * 請求日誌 Filter。
 * <p>
 * 在每個 HTTP 請求進來時：
 * <ol>
 *   <li>產生 traceId（UUID 前 8 碼）放入 MDC，方便 log pattern 印出</li>
 *   <li>記錄請求進入：method + uri</li>
 *   <li>實際執行請求</li>
 *   <li>記錄請求結束：status + 耗時 (ms)</li>
 *   <li>清掉 MDC，避免污染其他執行緒</li>
 * </ol>
 * </p>
 */
@Slf4j
@Component
@Order(1) // 數字越小越早執行
public class RequestLogFilter implements Filter {

    private static final String TRACE_ID = "traceId";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // 產生 8 碼短 traceId 並放入 MDC，logback pattern %X{traceId} 才能印出
        String traceId = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        MDC.put(TRACE_ID, traceId);

        long start = System.currentTimeMillis();
        log.info(">>> {} {}", req.getMethod(), req.getRequestURI());

        try {
            // 把控制權交給下一個 filter 或 servlet（最終會到 Controller）
            chain.doFilter(request, response);
        } finally {
            long cost = System.currentTimeMillis() - start;
            log.info("<<< status={} cost={}ms", res.getStatus(), cost);
            // 一定要清，否則 thread pool 重用執行緒時會帶到舊值
            MDC.remove(TRACE_ID);
        }
    }
}
