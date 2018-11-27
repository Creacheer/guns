package com.stylefeng.guns.modular.assistantManagement.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.CmAssistant;
import com.stylefeng.guns.modular.assistantManagement.service.ICmAssistantService;

/**
 * 助教管理控制器
 *
 * @author fengshuonan
 * @Date 2018-11-27 15:09:34
 */
@Controller
@RequestMapping("/cmAssistant")
public class CmAssistantController extends BaseController {

    private String PREFIX = "/assistantManagement/cmAssistant/";

    @Autowired
    private ICmAssistantService cmAssistantService;

    /**
     * 跳转到助教管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "cmAssistant.html";
    }

    /**
     * 跳转到添加助教管理
     */
    @RequestMapping("/cmAssistant_add")
    public String cmAssistantAdd() {
        return PREFIX + "cmAssistant_add.html";
    }

    /**
     * 跳转到修改助教管理
     */
    @RequestMapping("/cmAssistant_update/{cmAssistantId}")
    public String cmAssistantUpdate(@PathVariable Integer cmAssistantId, Model model) {
        CmAssistant cmAssistant = cmAssistantService.selectById(cmAssistantId);
        model.addAttribute("item",cmAssistant);
        LogObjectHolder.me().set(cmAssistant);
        return PREFIX + "cmAssistant_edit.html";
    }

    /**
     * 获取助教管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return cmAssistantService.selectList(null);
    }

    /**
     * 新增助教管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CmAssistant cmAssistant) {
        cmAssistantService.insert(cmAssistant);
        return SUCCESS_TIP;
    }

    /**
     * 删除助教管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer cmAssistantId) {
        cmAssistantService.deleteById(cmAssistantId);
        return SUCCESS_TIP;
    }

    /**
     * 修改助教管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CmAssistant cmAssistant) {
        cmAssistantService.updateById(cmAssistant);
        return SUCCESS_TIP;
    }

    /**
     * 助教管理详情
     */
    @RequestMapping(value = "/detail/{cmAssistantId}")
    @ResponseBody
    public Object detail(@PathVariable("cmAssistantId") Integer cmAssistantId) {
        return cmAssistantService.selectById(cmAssistantId);
    }
}
