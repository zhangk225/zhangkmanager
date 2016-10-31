import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

import com.zhangk.baseframe.common.util.CommonUtil;

public class TestCommon {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// String hostip = "192.168.10.3";
		// String hostid = "28b65254-5b1c-4655-9c75-8cae13c5ecb1";
		// String username = "root";
		// String password = "rootroot";
		// String ss=JingcloudCommon.registerSecHost(hostip, hostid, username,
		// password);
		// System.out.println(ss);
		// long ip=446859069;
		// System.out.println(CommonUtil.macAdd0("6d40c000007"));

		// TemplateRuleVO tr = new TemplateRuleVO("sina", "1",
		// "0.0.0.0/0", "0-65535", "101,10008,10009,10010",
		// "",
		// "2", VmsRuleType.User, 1l,
		// new Date());
		// tr.setCheckPriority(1l);
		// tr.setCheckGids("");
		// tr.setCheckSids("111,112");
		// TemplateRuleBean trb = TemplateRuleBean.getTemplateRuleBean(tr);
		// String json = GsonUtil.toJson(trb);
		// System.out.println(json);
		// Date date = new Date();
//		String zone = null;
//		zone = zone == null ? "" : zone;
//		TimeZone tz = TimeZone.getTimeZone(zone);
//		String pp = tz.getDisplayName();
//		System.out.println(pp);
//		// String ds = CommonUtil.dateUtil_dtos(date, zone);
//		String ds = "2014-06-10 22:41:59";
//		Date dd;
//		try {
//			dd = CommonUtil.date_LocalStodGMT(ds);
//			System.out.println(CommonUtil.date_LocalDtos(dd, ""));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println(TimeZone.getDefault().getID());

		// SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		// Calendar cal = Calendar.getInstance();
		// System.out.println(ft.format(cal.getTime()));
		// System.out.println(cal.get(Calendar.YEAR));
		// System.out.println(cal.get(Calendar.MONTH));
	}

}
