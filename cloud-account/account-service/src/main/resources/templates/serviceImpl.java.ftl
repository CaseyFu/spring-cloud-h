package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
* @ClassName ${table.serviceImplName}
* @Author ${author}
* @Description ${table.serviceImplName}
* @Date ${cfg.date}
*/

@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {
    private final ${table.mapperName} ${table.mapperName?uncap_first};

    @Autowired
    public ${table.serviceImplName}(${table.mapperName} ${table.mapperName?uncap_first}){
        this.${table.mapperName?uncap_first} = ${table.mapperName?uncap_first};
    }

}
</#if>
