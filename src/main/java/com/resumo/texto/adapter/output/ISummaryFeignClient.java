package com.resumo.texto.adapter.output;

import com.resumo.texto.dto.request.SummaryRequestDto;
import com.resumo.texto.dto.response.SummaryItemResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient (name = "${feign.api.name}", url = "${feign.api.url}")
public interface ISummaryFeignClient {

    @PostMapping
    List<SummaryItemResponse> generateSummary(@RequestBody SummaryRequestDto text,
                                              @RequestHeader("Authorization") String authorization);
}
