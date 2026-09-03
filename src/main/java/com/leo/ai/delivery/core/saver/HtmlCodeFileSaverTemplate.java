package com.leo.ai.delivery.core.saver;

import cn.hutool.core.util.StrUtil;
import com.leo.ai.delivery.ai.model.HtmlCodeResult;
import com.leo.ai.delivery.exception.BusinessException;
import com.leo.ai.delivery.exception.ErrorCode;
import com.leo.ai.delivery.model.enums.CodeGenTypeEnum;

/**
 * HTML代码文件保存器
 *
 * @author leo
 */
public class HtmlCodeFileSaverTemplate extends CodeFileSaverTemplate<HtmlCodeResult> {

    @Override
    protected CodeGenTypeEnum getCodeType() {
        return CodeGenTypeEnum.HTML;
    }

    @Override
    protected void saveFiles(HtmlCodeResult result, String baseDirPath) {
        writeToFile(baseDirPath, "index.html", result.getHtmlCode());
    }

    @Override
    protected void validateInput(HtmlCodeResult result) {
        super.validateInput(result);
        // HTML 代码不能为空
        if (StrUtil.isBlank(result.getHtmlCode())) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "HTML 代码不能为空");
        }
    }
}
