import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		// TemplateService hs = (TemplateService) applicationContext
		// .getBean("templateServiceImpl");
		// TemplateArgs templateArgs=new TemplateArgs();
		// templateArgs.setTemplateName("zhangk225");
		// templateArgs.setTemplateDescription("zhangk225");
		// // hs.addtemplate(templateArgs);
		// System.out.println(hs.getTemplateRuleList(67l).size());

//		 HostService hs = (HostService) applicationContext
//		 .getBean("hostServiceImpl");
//		 hs.findVm(1l);
		// List<VmResponse> vms = hs.getVmList(4l);
		// System.out.println(vms.size());
//		TemplateService templateService = (TemplateService) applicationContext
//				 .getBean("templateServiceImpl");
//		DataSynchronizeService hostServices = (DataSynchronizeService) applicationContext
//				.getBean("dataSynchronizeServiceImpl");
//		List<HostAffixVO> halist = hostServices.getHostList();
//		if (halist != null && halist.size() > 0) {
//			for (HostAffixVO havo : halist) {
//				HostVO host = havo.getHostVO();
////				Set<VmInstanceVO> tt=host.getVmInstanceVOs();
////				System.out.println(tt.size());
//				List<HostDetailsVO> hostDetailsVOs = hs
//						.getHostDetailsList(host.getId());
//				System.out.println(hostDetailsVOs.size());
//			}
//		}
//		Condition[] cs = new Condition[1];
//		cs[0] = Nullable.isNull(JingcloudConstants.SQLFILED_REMOVED);
//		List<TemplateVO> templates = templateService.findByEgList(
//				new TemplateVO(), cs);
//		System.out.println(templates.size());
	}
}
