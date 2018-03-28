package com.example.mao.beautylife.data;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tecmry on 2018/3/26.
 */

public class NetArticleItemData implements Serializable {

    /**
     * tColors : [{"id":0,"userName":"可爱杏杏","brandName":"欧莱雅","imgUrl":"http://104.236.187.61:8080/noNameApp/img/可爱杏杏--欧莱雅--300.jpg","number":"300"},{"id":0,"userName":"Temi酱","brandName":"GIVENCHY","imgUrl":"http://104.236.187.61:8080/noNameApp/img/Temi酱--GIVENCHY--326.jpg","number":"326"},{"id":0,"userName":"盼盼高高","brandName":"RAD","imgUrl":"http://104.236.187.61:8080/noNameApp/img/盼盼高高--RAD--A12.jpg","number":"A12"},{"id":0,"userName":"Temi酱","brandName":"KIKO","imgUrl":"http://104.236.187.61:8080/noNameApp/img/Temi酱--KIKO--407.jpg","number":"407"}]
     * videos : [{"id":"81","pageid":"prxdf1409aa03115f8c0aa9318efe0b1cb0","title":"烧死人的圣诞限量新品！香缇卡冬季限量彩妆","duration":"272","coverUrl":"https://beauty.nosdn.127.net/beauty/img/3c4bf777-356f-45ac-9dc2-7873b5860bea.jpg","likeTimes":42,"authorId":"326718","nickname":"YouTube搬运工","authorimg":"https://beauty.nosdn.127.net/c20694fb-0220-43a0-8df1-baa5ae859e9c.jpeg","videourl":"http://vodsphn1rqs.vod.126.net/vodsphn1rqs/qaY7BolY_53174749_hd.mp4","label":"标签1---标签二","labels":["标签1","标签二"]},{"id":"80","pageid":"prx8d9c0108609d265a18165b2d1dc10748","title":"试色 |口红也能freestyle~夏日音乐节电光唇秒杀全场！","duration":"98","coverUrl":"https://beauty.nosdn.127.net/beauty/img/d65d388d-4536-44e7-805c-a125f8522f6a.png","likeTimes":4000,"authorId":"196803","nickname":"优里娜","authorimg":"https://beauty.nosdn.127.net/14593fc1-42e2-4638-a015-577c77b2a4b4.png","videourl":"http://vodsphn1rqs.vod.126.net/vodsphn1rqs/lbwyAd5E_5192108_hd.mp4","label":"标签1---标签二","labels":["标签1","标签二"]},{"id":"79","pageid":"prxa53573a18ef5762174f028829e6fd89e","title":"分享丨比基尼女神不仅爱健身更爱红妆，超美红色系口红推荐","duration":"268","coverUrl":"https://beauty.nosdn.127.net/beauty/img/10408325-2664-4c0e-9099-525963781423.png","likeTimes":854,"authorId":"589254","nickname":"XOXO粉红霏菲","authorimg":"https://beauty.nosdn.127.net/cbec1973-48f3-4395-bcff-7b9436b00788.png","videourl":"http://vodsphn1rqs.vod.126.net/vodsphn1rqs/wNvpQJoS_2358101_hd.mp4","label":"标签1---标签二","labels":["标签1","标签二"]},{"id":"78","pageid":"prx96c869f463be79b827985d66e4618434","title":"试色| Lancôme Matte Shaker超带感广告片","duration":"61","coverUrl":"https://beauty.nosdn.127.net/beauty/img/53edfb17-8d93-474d-85c8-20cdab6acc9b.jpg","likeTimes":1536,"authorId":"326718","nickname":"YouTube搬运工","authorimg":"https://beauty.nosdn.127.net/c20694fb-0220-43a0-8df1-baa5ae859e9c.jpeg","videourl":"http://vodsphn1rqs.vod.126.net/vodsphn1rqs/yLYJ8eU3_12745081_hd.mp4","label":"标签1---标签二","labels":["标签1","标签二"]}]
     * articles : [{"id":413,"pageid":"prx0e7c05365857a8fc2db1be7e457b7a95","title":"高田贤三 神圣莲花面霜","content":"# 新品抢鲜报 ★ 7月上市 ? 高田贤三 神圣莲花面霜 经过证实，莲花的细胞有独特的自我修复能力?将莲花的提取物放入面霜中，","coverUrl":"https://beauty.nosdn.127.net/609a8cb1-4045-4bf3-9e37-160cff765301.jpeg","likeTimes":11,"authorId":481056,"nickname":"美学种草酱","authorimg":"https://beauty.nosdn.127.net/beauty/img/15e316ad-7bd2-451c-b692-5c304a12ce1a.png","contentcoverUrl":"https://beauty.nosdn.127.net/609a8cb1-4045-4bf3-9e37-160cff765301.jpeg?imageView&quality=95&interlace=1&thumbnail=302y302&enlarge=1*https://beauty.nosdn.127.net/0b81582f-7b0f-476e-ac7b-a2d826641bb9.jpeg?imageView&quality=95&interlace=1&thumbnail=302y302&enlarge=1*","articlecontent":"# 新品抢鲜报\n★ 7月上市\n? 高田贤三 神圣莲花面霜\n经过证实，莲花的细胞有独特的自我修复能力?将莲花的提取物放入面霜中，具有抗衰老的功效。面霜的质地非常柔软，可以帮助肌肤再生，减少细纹?使脸部肌肤柔软光滑。\n?种草酱tip：这款莲花面霜属于抗衰老系列，比较适合熟龄肌哦✨莲花系列还有脸部精油和血清精华，本酱建议一起用效果比较好呢～"},{"id":412,"pageid":"prx62c51319f047412bd3ad0a622b34ec71","title":"Lebond 香氛牙膏","content":"# 新品抢鲜报  ★ 9月国内上市  ? LEBON 香氛牙膏 LEBON，源自于法国蔚蓝海岸，创办人是?海洋与大自然的爱好者，在旅行的过","coverUrl":"https://beauty.nosdn.127.net/8289c21a-cb66-449d-892b-beceeecb42cf.jpeg","likeTimes":9,"authorId":481056,"nickname":"美学种草酱","authorimg":"https://beauty.nosdn.127.net/beauty/img/15e316ad-7bd2-451c-b692-5c304a12ce1a.png","contentcoverUrl":"https://beauty.nosdn.127.net/8289c21a-cb66-449d-892b-beceeecb42cf.jpeg?imageView&quality=95&interlace=1&thumbnail=116y116&enlarge=1*https://beauty.nosdn.127.net/8438b737-085e-43da-870d-a0b340487aff.jpeg?imageView&quality=95&interlace=1&thumbnail=116y116&enlarge=1*https://beauty.nosdn.127.net/5d063360-abf4-42df-96ba-f10ac463ecbe.jpeg?imageView&quality=95&interlace=1&thumbnail=116y116&enlarge=1*https://beauty.nosdn.127.net/438acab9-76b4-435f-b0fe-126a04b18c6f.jpeg?imageView&quality=95&interlace=1&thumbnail=116y116&enlarge=1*https://beauty.nosdn.127.net/40bb3384-9c1b-4453-b056-955f2e377028.jpeg?imageView&quality=95&interlace=1&thumbnail=116y116&enlarge=1*https://beauty.nosdn.127.net/d6a5944c-3217-4746-b6ba-85b1a4ffaf5c.jpeg?imageView&quality=95&interlace=1&thumbnail=116y116&enlarge=1*https://beauty.nosdn.127.net/b016c39a-5ece-4c09-a772-ffc3869a3f92.jpeg?imageView&quality=95&interlace=1&thumbnail=116y116&enlarge=1*https://beauty.nosdn.127.net/1e788352-6be5-4306-9501-616ef90559f1.jpeg?imageView&quality=95&interlace=1&thumbnail=116y116&enlarge=1*","articlecontent":"# 新品抢鲜报 \n★ 9月国内上市 \n? LEBON 香氛牙膏\nLEBON，源自于法国蔚蓝海岸，创办人是?海洋与大自然的爱好者，在旅行的过程中收集各种想法及创作灵感。\n2017年推出含有有机成份的「LEBON香氛牙膏」系列，成功地结合了香水之城格拉斯的（高品质、天然香氛配方），而且不含有一般牙膏常见的有害❌化学物质，用天然成份取代调和?\n此次一共推出6️\u20e3款，分别为：\n?昂蒂布泳池/费拉角情怀/挥霍无度的甜/夏恋热带假期/诺卡利纳别墅/自然纯净的白\n? 种草酱Tip：这些牙膏的名字听起来很厉害呢，而且也超级貌美的，想来这么贵的牙膏应该是蛮好用的呢，就是不晓得香氛的牙膏会不会让嘴巴变得好闻呢?～"},{"id":411,"pageid":"prx3259fb558715d0cc8956e6af8720e130","title":"盛田屋 豆乳芦荟面膜","content":"# 新品抢鲜报 ★ 7月上市 ? 盛田屋 豆乳芦荟面膜（夏季限定） 豆乳あろえぱっく 盛田屋出「夏季限定」的睡眠面膜啦！主打补水和修复","coverUrl":"https://beauty.nosdn.127.net/c3e22152-239b-4ebe-9a1b-bce764aff24c.jpeg","likeTimes":9,"authorId":481056,"nickname":"美学种草酱","authorimg":"https://beauty.nosdn.127.net/beauty/img/15e316ad-7bd2-451c-b692-5c304a12ce1a.png","contentcoverUrl":"https://beauty.nosdn.127.net/c3e22152-239b-4ebe-9a1b-bce764aff24c.jpeg?imageView&quality=95&interlace=1&thumbnail=116y116&enlarge=1*https://beauty.nosdn.127.net/33763eb2-4b0d-4665-8bd7-562b02c167a9.jpeg?imageView&quality=95&interlace=1&thumbnail=116y116&enlarge=1*https://beauty.nosdn.127.net/5de506ee-9734-49f2-a325-1581513fa6e7.jpeg?imageView&quality=95&interlace=1&thumbnail=116y116&enlarge=1*https://beauty.nosdn.127.net/516b0990-7587-407e-a1aa-127a274caca8.jpeg?imageView&quality=95&interlace=1&thumbnail=116y116&enlarge=1*","articlecontent":"# 新品抢鲜报\n★ 7月上市\n? 盛田屋 豆乳芦荟面膜（夏季限定）\n豆乳あろえぱっく\n盛田屋出「夏季限定」的睡眠面膜啦！主打补水和修复功能，能够镇静和修复白天皮肤因紫外线收到的伤害~\n新的配方拥有肌肤所需要的三大保湿修护成分：✨豆乳乳杆菌、芦荟和透明质酸✨，能够轻松给肌肤补充水分。\n?种草酱Tip：睡眠面膜用起来炒鸡方便，但是这款的味道是比较\u201c浓郁\u201d的哦，不喜欢味道太香的小仙女们可能要稍微考虑一下啦～"},{"id":410,"pageid":"prx32671a341a147691d374e923c38d9bea","title":"Jill Stuart 温感净凉洗颜凝露","content":"# 新品抢鲜报 ★7.7 上市 ?吉尔\u2022斯图亚特 温感净凉洗颜凝露 温感洗颜凝露，使用的时候能舒缓地温热肌肤，带走黑头和污垢的同","coverUrl":"https://beauty.nosdn.127.net/1ac50452-dbc0-496b-b248-3191def9c2b8.jpeg","likeTimes":14,"authorId":481056,"nickname":"美学种草酱","authorimg":"https://beauty.nosdn.127.net/beauty/img/15e316ad-7bd2-451c-b692-5c304a12ce1a.png","contentcoverUrl":"https://beauty.nosdn.127.net/1ac50452-dbc0-496b-b248-3191def9c2b8.jpeg?imageView&quality=95&interlace=1&thumbnail=302y302&enlarge=1*https://beauty.nosdn.127.net/863e2cba-8c0d-4038-a9da-f4d15164824e.jpeg?imageView&quality=95&interlace=1&thumbnail=302y302&enlarge=1*","articlecontent":"# 新品抢鲜报\n★7.7 上市\n?吉尔\u2022斯图亚特 温感净凉洗颜凝露\n温感洗颜凝露，使用的时候能舒缓地温热肌肤，带走黑头和污垢的同时，还有收敛紧实肌肤的功效。清洁之后，会有清清凉的感觉哦~凝胶的质地保持了完美的厚度，所以不会对肌肤造成任何负担！\n凝胶中添加了各种大小和纹理的去角质颗粒，★甘露聚糖磨砂颗粒如海绵般柔软，能够放松肌肤★大桃仁和纤维素可以去除多余角质，★精细的杏仁和核桃仁则能疏通毛孔。\n ?种草酱Tip：清新的水果鸡尾酒香味，酸酸甜甜的清爽香气，感觉能让身心都充满夏季活力???\n这类去角质的产品建议每周使用1~2次就好啦~"}]
     * requestStatus : SUCCESS
     */

    private String requestStatus;
    private List<TColorsBean> tColors;
    private List<VideosBean> videos;
    private List<ArticlesBean> articles;

    public static NetArticleItemData objectFromData(String str) {

        return new Gson().fromJson(str, NetArticleItemData.class);
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public List<TColorsBean> getTColors() {
        return tColors;
    }

    public void setTColors(List<TColorsBean> tColors) {
        this.tColors = tColors;
    }

    public List<VideosBean> getVideos() {
        return videos;
    }

    public void setVideos(List<VideosBean> videos) {
        this.videos = videos;
    }

    public List<ArticlesBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesBean> articles) {
        this.articles = articles;
    }

    public static class TColorsBean {
        /**
         * id : 0
         * userName : 可爱杏杏
         * brandName : 欧莱雅
         * imgUrl : http://104.236.187.61:8080/noNameApp/img/可爱杏杏--欧莱雅--300.jpg
         * number : 300
         */

        private int id;
        private String userName;
        private String brandName;
        private String imgUrl;
        private String number;

        public static TColorsBean objectFromData(String str) {

            return new Gson().fromJson(str, TColorsBean.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }

    public static class VideosBean implements Serializable{
        /**
         * id : 81
         * pageid : prxdf1409aa03115f8c0aa9318efe0b1cb0
         * title : 烧死人的圣诞限量新品！香缇卡冬季限量彩妆
         * duration : 272
         * coverUrl : https://beauty.nosdn.127.net/beauty/img/3c4bf777-356f-45ac-9dc2-7873b5860bea.jpg
         * likeTimes : 42
         * authorId : 326718
         * nickname : YouTube搬运工
         * authorimg : https://beauty.nosdn.127.net/c20694fb-0220-43a0-8df1-baa5ae859e9c.jpeg
         * videourl : http://vodsphn1rqs.vod.126.net/vodsphn1rqs/qaY7BolY_53174749_hd.mp4
         * label : 标签1---标签二
         * labels : ["标签1","标签二"]
         */

        private String id;
        private String pageid;
        private String title;
        private String duration;
        private String coverUrl;
        private int likeTimes;
        private String authorId;
        private String nickname;
        private String authorimg;
        private String videourl;
        private String label;
        private List<String> labels;

        public static VideosBean objectFromData(String str) {

            return new Gson().fromJson(str, VideosBean.class);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPageid() {
            return pageid;
        }

        public void setPageid(String pageid) {
            this.pageid = pageid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public int getLikeTimes() {
            return likeTimes;
        }

        public void setLikeTimes(int likeTimes) {
            this.likeTimes = likeTimes;
        }

        public String getAuthorId() {
            return authorId;
        }

        public void setAuthorId(String authorId) {
            this.authorId = authorId;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAuthorimg() {
            return authorimg;
        }

        public void setAuthorimg(String authorimg) {
            this.authorimg = authorimg;
        }

        public String getVideourl() {
            return videourl;
        }

        public void setVideourl(String videourl) {
            this.videourl = videourl;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public List<String> getLabels() {
            return labels;
        }

        public void setLabels(List<String> labels) {
            this.labels = labels;
        }
    }

    public static class ArticlesBean {
        /**
         * id : 413
         * pageid : prx0e7c05365857a8fc2db1be7e457b7a95
         * title : 高田贤三 神圣莲花面霜
         * content : # 新品抢鲜报 ★ 7月上市 ? 高田贤三 神圣莲花面霜 经过证实，莲花的细胞有独特的自我修复能力?将莲花的提取物放入面霜中，
         * coverUrl : https://beauty.nosdn.127.net/609a8cb1-4045-4bf3-9e37-160cff765301.jpeg
         * likeTimes : 11
         * authorId : 481056
         * nickname : 美学种草酱
         * authorimg : https://beauty.nosdn.127.net/beauty/img/15e316ad-7bd2-451c-b692-5c304a12ce1a.png
         * contentcoverUrl : https://beauty.nosdn.127.net/609a8cb1-4045-4bf3-9e37-160cff765301.jpeg?imageView&quality=95&interlace=1&thumbnail=302y302&enlarge=1*https://beauty.nosdn.127.net/0b81582f-7b0f-476e-ac7b-a2d826641bb9.jpeg?imageView&quality=95&interlace=1&thumbnail=302y302&enlarge=1*
         * articlecontent : # 新品抢鲜报
         ★ 7月上市
         ? 高田贤三 神圣莲花面霜
         经过证实，莲花的细胞有独特的自我修复能力?将莲花的提取物放入面霜中，具有抗衰老的功效。面霜的质地非常柔软，可以帮助肌肤再生，减少细纹?使脸部肌肤柔软光滑。
         ?种草酱tip：这款莲花面霜属于抗衰老系列，比较适合熟龄肌哦✨莲花系列还有脸部精油和血清精华，本酱建议一起用效果比较好呢～
         */

        private int id;
        private String pageid;
        private String title;
        private String content;
        private String coverUrl;
        private int likeTimes;
        private int authorId;
        private String nickname;
        private String authorimg;
        private String contentcoverUrl;
        private String articlecontent;

        public static ArticlesBean objectFromData(String str) {

            return new Gson().fromJson(str, ArticlesBean.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPageid() {
            return pageid;
        }

        public void setPageid(String pageid) {
            this.pageid = pageid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public int getLikeTimes() {
            return likeTimes;
        }

        public void setLikeTimes(int likeTimes) {
            this.likeTimes = likeTimes;
        }

        public int getAuthorId() {
            return authorId;
        }

        public void setAuthorId(int authorId) {
            this.authorId = authorId;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAuthorimg() {
            return authorimg;
        }

        public void setAuthorimg(String authorimg) {
            this.authorimg = authorimg;
        }

        public String getContentcoverUrl() {
            return contentcoverUrl;
        }

        public void setContentcoverUrl(String contentcoverUrl) {
            this.contentcoverUrl = contentcoverUrl;
        }

        public String getArticlecontent() {
            return articlecontent;
        }

        public void setArticlecontent(String articlecontent) {
            this.articlecontent = articlecontent;
        }
    }
}
