package cn.mayu.yugioh.common.dto.transform;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDetail {

	// 卡片密码
	private String password;

	// 当前卡包的编号
	private String serial;

	// 中文名
	private String name;

	// 日文名
	private String nameJa;

	// 英文名
	private String nameEn;

	// nw名称
	private String nameNw;

	// 图片地址
	private String imgUrl;

	// 等级
	private Integer level;

	// 属性
	private String attribute;

	// 种族
	private String race;

	// 攻击力
	private String atk;

	// 防御力
	private String def;

	// 左刻度
	private String pendL;

	// 右刻度
	private String pendR;

	// 链接数
	private String link;

	// 效果
	private String desc;

	// 限制
	private String ban;

	// nw效果
	private String descNw;

	// 日文效果
	private String descJa;

	// 英文效果
	private String descEn;

	// 卡片类型
	private List<String> typeSt;

	// 连接方向
	private List<String> linkArrow;

	// 罕贵度
	private List<String> rare = new ArrayList<String>();

	// wiki
	private String adjust;

	// id
	private Integer id;

	// 卡类型
	private String typeVal;
}