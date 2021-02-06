package ${package.Controller};

import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
<#if swagger2>
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
</#if>
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
* @ClassName ${table.controllerName}
* @Author ${author}
* @Description ${table.controllerName}
* @Date ${cfg.date}
*/

<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
<#if swagger2>
@Api(tags = {"${entity}接口"})
</#if>
public class ${table.controllerName} {
    </#if>
    private final ${table.serviceName} ${table.serviceName?uncap_first};

    @Autowired
    public ${table.controllerName}(${table.serviceName} ${table.serviceName?uncap_first}){
        this.${table.serviceName?uncap_first} = ${table.serviceName?uncap_first};
    }

    <#if swagger2>
    @ApiOperation(value = "value", notes = "注释")
    </#if>
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    
}
</#if>
