import java.lang.reflect.Type;

import com.frame.util.json.Frame_QueryJobResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhangk.baseframe.common.util.gson.ApiResponseGsonHelper;

public class TestGson {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// HostBean hj = new HostBean();
		// NicBean nj = new NicBean();
		// VmBean vj = new VmBean();
		// nj.setId("1111");
		// nj.setMac("2222");
		// vj.setId("1111");
		// List<NicBean> njs = new ArrayList<NicBean>();
		// njs.add(nj);
		// vj.setNic(njs);
		// hj.setId("1111");
		// List<VmBean> vjs = new ArrayList<VmBean>();
		// vjs.add(vj);
		// hj.setVms(vjs);
		// String json = GsonUtil.toJson(hj);
		// System.out.println(json);
		// HostBean hj1 = GsonUtil.fromJson(json, HostBean.class);
		// System.out.println(hj1.getVms().get(0).getNic().get(0).getMac());

		// DeployBean deployBean = new DeployBean();
		// List<Map<String, String>> network = new ArrayList<Map<String,
		// String>>();
		// Map<String,String> map=new HashMap<String,String>();
		// map.put("aaaa", "11111");
		// map.put("bbbb", "22222");
		// network.add(map);
		// Map<String,String> map1=new HashMap<String,String>();
		// map1.put("aaaa", "33333");
		// map1.put("bbbb", "55555");
		// network.add(map1);
		// deployBean.setNetwork(network);
		// String json1 = GsonUtil.toJson(deployBean);
		// System.out.println(json1);
		// String
		// json2="[{\"group\":\"文件服务器\",\"apps\":[{\"id\":21015,\"name\":\"Windows文件服务v2（SMBv2）\"},{\"id\":21006,\"name\":\"文件传输协议（FTP）\"},{\"id\":21014,\"name\":\"Windows文件服务（SMB）\"}]},{\"group\":\"中间件\",\"apps\":[{\"id\":18003,\"name\":\"基于UDP的分布式计算环境的远程过程调用（DCERPC over UDP）\"},{\"id\":18002,\"name\":\"分布式计算环境的远程过程调用（DCERPC）\"}]},{\"group\":\"网站\",\"apps\":[{\"id\":40001,\"name\":\"超文本传输协议（HTTP）\"}]},{\"group\":\"即时通讯\",\"apps\":[{\"id\":15027,\"name\":\"Jabber协议\"},{\"id\":15026,\"name\":\"MSN Messenger\"}]},{\"group\":\"论坛\",\"apps\":[{\"id\":10007,\"name\":\"互联网中继聊天（IRC）\"}]},{\"group\":\"终端\",\"apps\":[{\"id\":3002,\"name\":\"安全Shell（SSH）\"}]},{\"group\":\"基本网络协议\",\"apps\":[{\"id\":3,\"name\":\"传输控制协议（TCP）\"},{\"id\":2,\"name\":\"用户数据包协议（UDP）\"},{\"id\":1,\"name\":\"Internet控制报文协议v4（ICMPv4）\"},{\"id\":4,\"name\":\"Internet控制报文协议v6（ICMPv6）\"}]},{\"group\":\"邮件\",\"apps\":[{\"id\":7004,\"name\":\"Internet消息访问协议（IMAP）\"},{\"id\":7005,\"name\":\"简单邮件传输协议（SMTP）\"}]}]";
		// Gson gson = ApiResponseGsonHelper.getBuilder().create();
		// Type type = new TypeToken<List<ProtoGroupBean>>() {
		// }.getType();
		// List<ProtoGroupBean>
		// protos=(List<ProtoGroupBean>)gson.fromJson(json2, type);
		// if(protos!=null&&protos.size()>0){
		// System.out.println(protos);
		// int i=1;
		// for(ProtoGroupBean proto:protos){
		// // proto.setGroupId(i+"");
		// }
		// }
		//
		// System.out.println(protos.size());
		// Jingcloud_QueryJobResponse qjr =new Jingcloud_QueryJobResponse();
		// qjr.setCreated(new Date());
		// qjr.setJobInstanceId("5555555555555");
		// qjr.setJobStatus(1);
		// Jingcloud_ErrorResponse ssz=new Jingcloud_ErrorResponse(1,"====");
		// ssz.setErrorCode(1);
		// ssz.setErrorText("5555");
		// qjr.setJobResult(ssz);
		// String ss=GsonUtil.toJson(qjr);
		// System.out.println(ss);
//		String message = "{\"jobstatus\":2,\"jobresult\":{\"errorcode\":503,\"errortext\":\"false\"},\"jobinstanceid\":\"b77e3904-86b2-456c-aebb-112a9c4a68b4\",\"created\":\"2014-12-08 14:21:16\"}";
//		Gson gson = ApiResponseGsonHelper.getBuilder().create();
//		Type type = new TypeToken<Frame_QueryJobResponse>() {
//		}.getType();
//		Frame_QueryJobResponse qjr = (Frame_QueryJobResponse) gson.fromJson(
//				message, type);
//		System.out.println(qjr.getJobResult());
//		message = "{\"jobstatus\":2,\"jobresult\":503,\"jobinstanceid\":\"b77e3904-86b2-456c-aebb-112a9c4a68b4\",\"created\":\"2014-12-08 14:21:16\"}";
//		qjr.setJobResult(message);
		// Gson gson1 = ApiResponseGsonHelper.getBuilder().create();
		// Type type1 = new TypeToken<Jingcloud_QueryJobResponse>() {
		// }.getType();
		// Jingcloud_QueryJobResponse qjr1 = (Jingcloud_QueryJobResponse) gson
		// .fromJson(message, type);
		// ErrorResponse ssz=new ErrorResponse();
		// ssz.setErrorCode(1);
		// ssz.setErrorText("5555");
		// qjr1.setJobResult(ssz);
		// String yy=GsonUtil.toJson(qjr.getJobResult());
		// System.out.println(yy);
//		Gson gson1 = ApiResponseGsonHelper.getBuilder().create();
//		Type type1 = new TypeToken<Frame_QueryJobResponse>() {
//		}.getType();
//		String ss = gson1.toJson(qjr);
//		System.out.println(ss);
		// System.out.println(qjr1.getJobResult());
		System.out.println("===");
	}

}
