package cn.mayu.yugioh.common.mybatis.generator.plugin;

import java.util.List;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class JpaLombokPlugin extends PluginAdapter {

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		return false;
	}

	@Override
	public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		return false;
	}

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addImportedType("lombok.Data");
		topLevelClass.addImportedType("javax.persistence.Entity");
		topLevelClass.addImportedType("javax.persistence.Table");
		topLevelClass.addAnnotation("@Data");
		topLevelClass.addAnnotation("@Entity");
		topLevelClass.addAnnotation("@Table(name = \"" + introspectedTable.getTableConfiguration().getTableName() + "\")");
		return true;
	}
	
	@Override
	public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		List<IntrospectedColumn> columns = introspectedTable.getPrimaryKeyColumns();
		for (IntrospectedColumn column : columns) {
			if (column == introspectedColumn) {
				topLevelClass.addImportedType("javax.persistence.Id");
				field.addAnnotation("@Id");
				break;
			}
		}
		
		if (introspectedColumn.isAutoIncrement()) {
			topLevelClass.addImportedType("javax.persistence.GenerationType");
			topLevelClass.addImportedType("javax.persistence.GeneratedValue");
			field.addAnnotation("@GeneratedValue(strategy = GenerationType.IDENTITY)");
		}
		
		topLevelClass.addImportedType("javax.persistence.Column");
//		if (introspectedColumn.getDefaultValue() != null) {
//			field.addAnnotation("@Column(name = \"" + introspectedColumn.getActualColumnName() + "\", insertable = false, columnDefinition = \"" + introspectedColumn.getJdbcTypeName() + " DEFAULT " + introspectedColumn.getDefaultValue() + "\")");
//		} else
		if (introspectedColumn.getDefaultValue() != null && introspectedColumn.getDefaultValue().equals("CURRENT_TIMESTAMP")) {
			field.addAnnotation("@Column(insertable = false, updatable = false, name = \"" + introspectedColumn.getActualColumnName() + "\", columnDefinition = \"TIMESTAMP DEFAULT CURRENT_TIMESTAMP\")");
		} else
		if (introspectedColumn.getActualColumnName().equals("modify_time") && introspectedColumn.getJdbcTypeName().equals("TIMESTAMP")) {
			topLevelClass.addImportedType("org.hibernate.annotations.UpdateTimestamp");
			field.addAnnotation("@UpdateTimestamp");
			field.addAnnotation("@Column(name = \"" + introspectedColumn.getActualColumnName() + "\")");
		} else 
		{
			field.addAnnotation("@Column(name = \"" + introspectedColumn.getActualColumnName() + "\")");
		}
		
        if(field.getType().getShortName().equals("Byte") || field.getType().getShortName().equals("Boolean")) {
        	field.setType(new FullyQualifiedJavaType("java.lang.Integer"));
        }
        
        if(field.getType().getShortName().equals("Date")) {
        	topLevelClass.addImportedType("java.time.LocalDateTime");
        	field.setType(new FullyQualifiedJavaType("java.time.LocalDateTime"));
        }
        
		return true;
	}
}
