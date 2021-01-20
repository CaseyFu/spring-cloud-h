package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
* @InterfaceName ${table.serviceName}
* @Author ${author}
* @Description ${table.serviceName}
* @Date ${cfg.date}
*/

<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

}
</#if>
