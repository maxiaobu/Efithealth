package com.efithealth.app.maxiaobu.common;

/**
 * Created by 马小布 on 2016/8/26.
 */
public class Index {
/*SP_BEGIN   SP_BEGIN   SP_BEGIN   SP_BEGIN   SP_BEGIN   SP_BEGIN   SP_BEGIN   SP_BEGIN   */
    /**
     * 经度
     */
    public static final String LONGITUDE = "longitude";
    /**
     * 纬度
     */
    public static final String LATITUDE = "latitude";

    /**
     * ？？？？？？
     */
    public static final String CITY = "city";
    /**
     * 城市名
     */
    public static final String CITY_NAME = "city_name";

    /**
     * 用户id
     */
    public static final String MEMID = "memid";
/*SP_END   SP_END   SP_END   SP_END   SP_END   SP_END   SP_END   SP_END   */


/*----URL_BEGIN   URL_BEGIN   URL_BEGIN   URL_BEGIN   URL_BEGIN   URL_BEGIN   ------*/

    /**
     * 内网
     * http://192.168.1.121:8080/efithealth/
     */
    public static final String URL_HOST = "http://192.168.1.121:8080/efithealth/";
    /**
     * url基类
     * http://123.56.195.32:8080/efithealth/
     */
    public static final String URL_BASE = URL_HOST;//"http://123.56.195.32:8080/efithealth/";
    /**
     * 订餐列表
     * http://192.168.1.121:8080/efithealth/mbFoodmers.do?memid=M000439&mertype=all&pageIndex=1&sorttype=sorttype
     */
    public static final String URL_LUNCH_LIST = URL_BASE + "mbFoodmers.do";

    /**
     * 订餐详情
     * 商品id
     * http://192.168.1.121:8080/efithealth/mgetFoodmers.do?merid=M000019
     */
    public static final String URL_FOOD_DETAIL = URL_BASE + "mgetFoodmers.do";


    /**
     * 订单确认
     * 商品id
     * http://192.168.1.121:8080/efithealth/mmeById.do?memid=M000439
     */
    public static final String URL_USER_INFO_BY_ID = URL_BASE + "mmeById.do";

    /**
     * 保存订单信息
     * memid（会员编号）
     * mernum （商品数量）
     * ordamt （订单金额）
     * isShopcart（是否加入过购物车）0、1
     * 商品id foodmers ="{ 'foodmers': [{ 'merid': 'xxxx', 'buynum':'1'},{'merid': 'yyyy', 'buynum':'2'},{'merid': 'zzz', 'buynum':'5'}]}"
     * http://192.168.1.121:8080/efithealth/mfordersave.do?memid=M000439&mernum=2&ordamt=1000&isShopcart=0&foodmers={foodmers:[{merid:M000019,buynum:2}]}
     * ，｛｝
     * retrun {"msgFlag":"1","msgContent":"","ordno":["FO-20160826-474"]}
     */
    public static final String URL_SAVE_ORDER_INDO = URL_BASE + "mfordersave.do";

    /**
     * 配餐订单列表
     * pageIndex: pageIndex++, //当前页码
     * listtype: "forderlist",//写死，就写他，区分出订餐订单
     * memid: getMemid()//用户id
     * http://192.168.1.121:8080/efithealth/morderlist.do?memid=M000455&listtype=forderlist&pageIndex=1
     */
    public static final String URL_FOOD_ORDER_LIST = URL_BASE + "morderlist.do";

    /**
     * 按摩订单列表
     * pageIndex: pageIndex++, //当前页码
     * listtype: "morderlist",//写死，就写他，区分出按摩订单
     * memid: getMemid()//用户id
     * http://192.168.1.121:8080/efithealth/mmassageorderList.do?memid=M000439&listtype=morderlist&pageIndex=1
     */
    public static final String URL_MSJ_ORDER_LIST = URL_BASE + "mmassageorderList.do";//
    /**
     * 用户账户信息
     * http://192.168.1.121:8080/efithealth/maccount.do?memid=M000439
     * http://192.168.1.121:8080/efithealth/maccount.do
     */
    public static final String URL_ACCOUNT_INFO = URL_BASE + "maccount.do";
    /**
     * 配餐订单的e币支付
     * <p>
     * http://192.168.1.121:8080/efithealth/mfoodoutBYcoin.do?ordno={"ordno":["FO-20160825-305","FO-20160825-306"]}
     * {"msgFlag":"1","msgContent":"配餐支付成功"}
     */
    public static final String URL_EBI_PAY = URL_BASE + "mfoodoutBYcoin.do";

    /**
     * e币+微信支付
     * http://192.168.1.121:8080/efithealth/mwxpay.do?ordno=FO-20160825-305&orderamt=0
     * 参数：ordno（订单id ），orderamt(支付标识)=0    选择弈币支付为0
     * return:{"appid":"wxe774923041503e14","partnerid":"1335056901","prepayid":"wx20160826150627bf3f7c514f0482801153","package":"Sign=WXPay","noncestr":"gA6ffRHZFGkjfBYS","timestamp":"1472195177","sign":"06BD02175979961E8F3C4468B244FEF9"}
     */
    public static final String URL_EBI_WXIN_PAY = URL_BASE + "mwxpay.do";

    /**
     * 微信支付
     * http://192.168.1.121:8080/efithealth/mwxpay.do?ordno=FO-20160825-305&orderamt=1
     * 参数：ordno（订单id ），orderamt(支付标识)=1    选择弈币支付为0 否为1
     * return:{"appid":"wxe774923041503e14","partnerid":"1335056901","prepayid":"wx20160826150627bf3f7c514f0482801153","package":"Sign=WXPay","noncestr":"gA6ffRHZFGkjfBYS","timestamp":"1472195177","sign":"06BD02175979961E8F3C4468B244FEF9"}
     */
    public static final String URL_WXIN_PAY = URL_BASE + "mwxpay.do";

    /**
     * 单个订单修改地址
     * http://192.168.1.121:8080/efithealth/mupdateAddress.do?updateMode=all&region
     * <p>
     * 参数：updateMode（all，tomorrow）
     * region（区域）
     * address（地址）
     * ordno（订单编号）
     */
    public static final String URL_MODIFY_ORDER_ADDRESS = URL_BASE + "mupdateAddress.do";

    /**
     * 延期
     * http://192.168.1.121:8080/efithealth/mextension.do?ordno=FO-20160726-170
     * {"msgFlag":"1","msgContent":"延期成功"}
     */
    public static final String URL_DELAY_ORDER = URL_BASE + "mextension.do";

    /**
     * 取消订单
     * http://192.168.1.121:8080/efithealth/mcancelForder.do?ordno=FO-20160726-170
     * {"msgFlag":"1","msgContent":"取消订单成功"}
     */
    public static final String URL_CANCEL_ORDER = URL_BASE + "mcancelForder.do";

    /**
     * 订单详情
     * http://192.168.1.121:8080/efithealth/mselectForderByOrdno.do?ordno=FO-20160726-170
     */
    public static final String URL_FOOD_ORDER_DETAIL = URL_BASE + "mselectForderByOrdno.do";

    /**
     * 查看配送详情
     * <p>
     * http://192.168.1.121:8080/efithealth/mselectDelivery.do?ordno=FO-20160726-170
     */
    public static final String URL_FOOD_DISPATCH_DETAIL = URL_BASE + "mselectDelivery.do";

    /**
     * 教练列表coaches_list
     * pageIndex:页码(1开始),
     * memid: 当前用户id ,
     * latitude: 纬度,
     * longitude: 经度,
     * sorttype: 不限；按距离、按好评(evascore)、按热度(coursetimes),
     * gender:不限；男(1)；女(0)
     * <p>
     * http://192.168.1.121:8080/efithealth/mcoachList.do?memid=M000439&pageIndex=1&latitude=41.811237&longitude=123.432856&sorttytpe=distance&gender=all
     */
    public static final String URL_COACHES_LIST = URL_BASE + "mcoachList.do";

    /**
     * 教练详情coaches_list
     * pageIndex:页码(1开始),
     * memid: 当前用户id ,
     * latitude: 纬度,
     * longitude: 经度,
     * sorttype: 不限；按距离、按好评(evascore)、按热度(coursetimes),
     * gender:不限；男(1)；女(0)
     * <p>
     * http://192.168.1.121:8080/efithealth/mcoachList.do?memid=M000439&pageIndex=1&latitude=41.811237&longitude=123.432856&sorttytpe=distance&gender=all
     */
    public static final String URL_COACHES_DETAIL = URL_BASE + "mbcoach.do";


/*-----URL_END   URL_END   URL_END   URL_END   URL_END   URL_END   URL_END   -------*/
}
