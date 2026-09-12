package com.leo.ai.delivery.langgraph4j.tools;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.leo.ai.delivery.langgraph4j.model.ImageResource;
import com.leo.ai.delivery.langgraph4j.model.enums.ImageCategoryEnum;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 图片收集工具（插画图片）
 */
@Slf4j
@Component
public class SketchValleyTool {

    private static final String API_URL = "https://sketchvalley.com/api/illustrations.php?search=%s&limit=%d";

    @Tool("搜索插画图片，用于网站美化和装饰")
    public List<ImageResource> searchIllustrations(@P("搜索关键词") String query) {
        List<ImageResource> imageList = new ArrayList<>();
        int searchCount = 12;
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String apiUrl = String.format(API_URL, encodedQuery, searchCount);

        // 使用 try-with-resources 自动释放 HTTP 资源
        try (HttpResponse response = HttpRequest.get(apiUrl).timeout(10000).execute()) {
            if (!response.isOk()) {
                return imageList;
            }
            JSONObject result = JSONUtil.parseObj(response.body());
            if (!result.getBool("ok", false)) {
                return imageList;
            }
            JSONArray illustrations = result.getJSONArray("illustrations");
            if (illustrations == null || illustrations.isEmpty()) {
                return imageList;
            }
            int actualCount = Math.min(searchCount, illustrations.size());
            for (int i = 0; i < actualCount; i++) {
                JSONObject illustration = illustrations.getJSONObject(i);
                String title = illustration.getStr("title", "插画");
                String pngUrl = illustration.getStr("png_url", "");
                if (StrUtil.isNotBlank(pngUrl)) {
                    imageList.add(ImageResource.builder()
                            .category(ImageCategoryEnum.ILLUSTRATION)
                            .description(title)
                            .url(pngUrl)
                            .build());
                }
            }
        } catch (Exception e) {
            log.error("搜索插画失败：{}", e.getMessage(), e);
        }
        return imageList;
    }
}
