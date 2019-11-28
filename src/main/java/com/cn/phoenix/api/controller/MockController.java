package com.cn.phoenix.api.controller;

import com.cn.phoenix.api.annotation.UserLoginToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lupq
 * @date 2019/10/30 13:29
 */
@RestController
public class MockController {

    @UserLoginToken
    @RequestMapping(value = "/article/list")
    public Object article(){

        return "{\n" +
                "  \"code\": 200,\n" +
                "  \"data\": {\n" +
                "    \"total\": 100,\n" +
                "    \"items\": [\n" +
                "      {\n" +
                "        \"id\": 1,\n" +
                "        \"timestamp\": 1504344193935,\n" +
                "        \"author\": \"Jason\",\n" +
                "        \"reviewer\": \"Shirley\",\n" +
                "        \"title\": \"Dwuhsweg Lisskqbf Aspxe Gzgvwm Xvnx Bejnrgz Nvwoagiyd Omhv Sfbd\",\n" +
                "        \"content_short\": \"mock data\",\n" +
                "        \"content\": \"<p>I am testing data, I am testing data.</p><p><img src=\\\"https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943\\\"></p>\",\n" +
                "        \"forecast\": 9.16,\n" +
                "        \"importance\": 1,\n" +
                "        \"type\": \"EU\",\n" +
                "        \"status\": \"published\",\n" +
                "        \"display_time\": \"2015-09-20 16:50:49\",\n" +
                "        \"comment_disabled\": true,\n" +
                "        \"pageviews\": 393,\n" +
                "        \"image_uri\": \"https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3\",\n" +
                "        \"platforms\": [\n" +
                "          \"a-platform\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 2,\n" +
                "        \"timestamp\": 309479518606,\n" +
                "        \"author\": \"Jose\",\n" +
                "        \"reviewer\": \"Jason\",\n" +
                "        \"title\": \"Tysooylr Xfthcl Cwprfrclv Tctqmbh Yjuof Vdib Rvulsrhun Jyyls Krbfncrp\",\n" +
                "        \"content_short\": \"mock data\",\n" +
                "        \"content\": \"<p>I am testing data, I am testing data.</p><p><img src=\\\"https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943\\\"></p>\",\n" +
                "        \"forecast\": 62.84,\n" +
                "        \"importance\": 2,\n" +
                "        \"type\": \"JP\",\n" +
                "        \"status\": \"published\",\n" +
                "        \"display_time\": \"1972-08-09 02:48:08\",\n" +
                "        \"comment_disabled\": true,\n" +
                "        \"pageviews\": 1559,\n" +
                "        \"image_uri\": \"https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3\",\n" +
                "        \"platforms\": [\n" +
                "          \"a-platform\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 3,\n" +
                "        \"timestamp\": 1497266622837,\n" +
                "        \"author\": \"Cynthia\",\n" +
                "        \"reviewer\": \"Patricia\",\n" +
                "        \"title\": \"Qdakx Izrr Tttf Udqql Gkyib Pxxj Utgknkmx Mces Sxo Kwbxh\",\n" +
                "        \"content_short\": \"mock data\",\n" +
                "        \"content\": \"<p>I am testing data, I am testing data.</p><p><img src=\\\"https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943\\\"></p>\",\n" +
                "        \"forecast\": 40.12,\n" +
                "        \"importance\": 2,\n" +
                "        \"type\": \"US\",\n" +
                "        \"status\": \"published\",\n" +
                "        \"display_time\": \"1980-08-11 10:26:56\",\n" +
                "        \"comment_disabled\": true,\n" +
                "        \"pageviews\": 4283,\n" +
                "        \"image_uri\": \"https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3\",\n" +
                "        \"platforms\": [\n" +
                "          \"a-platform\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 4,\n" +
                "        \"timestamp\": 1301323059531,\n" +
                "        \"author\": \"Gary\",\n" +
                "        \"reviewer\": \"Shirley\",\n" +
                "        \"title\": \"Mhbu Obkdmtk Wwihfle Dxjwoyedim Ujbaicqvrj Trbosfle\",\n" +
                "        \"content_short\": \"mock data\",\n" +
                "        \"content\": \"<p>I am testing data, I am testing data.</p><p><img src=\\\"https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943\\\"></p>\",\n" +
                "        \"forecast\": 83.83,\n" +
                "        \"importance\": 2,\n" +
                "        \"type\": \"US\",\n" +
                "        \"status\": \"draft\",\n" +
                "        \"display_time\": \"1996-05-30 03:20:12\",\n" +
                "        \"comment_disabled\": true,\n" +
                "        \"pageviews\": 1478,\n" +
                "        \"image_uri\": \"https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3\",\n" +
                "        \"platforms\": [\n" +
                "          \"a-platform\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 5,\n" +
                "        \"timestamp\": 877449974759,\n" +
                "        \"author\": \"Elizabeth\",\n" +
                "        \"reviewer\": \"Christopher\",\n" +
                "        \"title\": \"Hsayob Biglo Bklv Wfqg Nslei Yjj Hycig\",\n" +
                "        \"content_short\": \"mock data\",\n" +
                "        \"content\": \"<p>I am testing data, I am testing data.</p><p><img src=\\\"https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943\\\"></p>\",\n" +
                "        \"forecast\": 98.42,\n" +
                "        \"importance\": 2,\n" +
                "        \"type\": \"CN\",\n" +
                "        \"status\": \"published\",\n" +
                "        \"display_time\": \"1980-12-25 09:03:28\",\n" +
                "        \"comment_disabled\": true,\n" +
                "        \"pageviews\": 2743,\n" +
                "        \"image_uri\": \"https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3\",\n" +
                "        \"platforms\": [\n" +
                "          \"a-platform\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 6,\n" +
                "        \"timestamp\": 838087969797,\n" +
                "        \"author\": \"George\",\n" +
                "        \"reviewer\": \"Angela\",\n" +
                "        \"title\": \"Vybmmb Qesicvo Euwuip Ohlnqy Ddoj\",\n" +
                "        \"content_short\": \"mock data\",\n" +
                "        \"content\": \"<p>I am testing data, I am testing data.</p><p><img src=\\\"https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943\\\"></p>\",\n" +
                "        \"forecast\": 2.91,\n" +
                "        \"importance\": 2,\n" +
                "        \"type\": \"JP\",\n" +
                "        \"status\": \"deleted\",\n" +
                "        \"display_time\": \"1999-03-01 17:26:15\",\n" +
                "        \"comment_disabled\": true,\n" +
                "        \"pageviews\": 4878,\n" +
                "        \"image_uri\": \"https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3\",\n" +
                "        \"platforms\": [\n" +
                "          \"a-platform\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 7,\n" +
                "        \"timestamp\": 557695147489,\n" +
                "        \"author\": \"Deborah\",\n" +
                "        \"reviewer\": \"Mark\",\n" +
                "        \"title\": \"Shminj Skg Xafhw Ndm Rspdbct Ucuftvdlwd Qojidixk Cyo Ehok Lmjr\",\n" +
                "        \"content_short\": \"mock data\",\n" +
                "        \"content\": \"<p>I am testing data, I am testing data.</p><p><img src=\\\"https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943\\\"></p>\",\n" +
                "        \"forecast\": 69.41,\n" +
                "        \"importance\": 2,\n" +
                "        \"type\": \"US\",\n" +
                "        \"status\": \"deleted\",\n" +
                "        \"display_time\": \"2013-10-13 23:18:56\",\n" +
                "        \"comment_disabled\": true,\n" +
                "        \"pageviews\": 3330,\n" +
                "        \"image_uri\": \"https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3\",\n" +
                "        \"platforms\": [\n" +
                "          \"a-platform\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 8,\n" +
                "        \"timestamp\": 1367241062982,\n" +
                "        \"author\": \"John\",\n" +
                "        \"reviewer\": \"Linda\",\n" +
                "        \"title\": \"Oufctk Lbljds Brwpbnfl Nbzw Hfcix Lmqhk Sahfiv Fnxqjxk Tvztcrej Xqhwylo\",\n" +
                "        \"content_short\": \"mock data\",\n" +
                "        \"content\": \"<p>I am testing data, I am testing data.</p><p><img src=\\\"https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943\\\"></p>\",\n" +
                "        \"forecast\": 77.68,\n" +
                "        \"importance\": 2,\n" +
                "        \"type\": \"JP\",\n" +
                "        \"status\": \"draft\",\n" +
                "        \"display_time\": \"1977-09-11 14:46:53\",\n" +
                "        \"comment_disabled\": true,\n" +
                "        \"pageviews\": 4784,\n" +
                "        \"image_uri\": \"https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3\",\n" +
                "        \"platforms\": [\n" +
                "          \"a-platform\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 9,\n" +
                "        \"timestamp\": 978791320988,\n" +
                "        \"author\": \"Shirley\",\n" +
                "        \"reviewer\": \"Larry\",\n" +
                "        \"title\": \"Xcgscsoit Actjdaqeg Xxmpgmr Tncyv Dbokmtumff\",\n" +
                "        \"content_short\": \"mock data\",\n" +
                "        \"content\": \"<p>I am testing data, I am testing data.</p><p><img src=\\\"https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943\\\"></p>\",\n" +
                "        \"forecast\": 75.65,\n" +
                "        \"importance\": 2,\n" +
                "        \"type\": \"JP\",\n" +
                "        \"status\": \"draft\",\n" +
                "        \"display_time\": \"1990-11-12 03:23:39\",\n" +
                "        \"comment_disabled\": true,\n" +
                "        \"pageviews\": 4566,\n" +
                "        \"image_uri\": \"https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3\",\n" +
                "        \"platforms\": [\n" +
                "          \"a-platform\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 10,\n" +
                "        \"timestamp\": 1362676822968,\n" +
                "        \"author\": \"Linda\",\n" +
                "        \"reviewer\": \"Richard\",\n" +
                "        \"title\": \"Wdtesc Gtnvp Rvrveoqjq Raxzkqv Hyb\",\n" +
                "        \"content_short\": \"mock data\",\n" +
                "        \"content\": \"<p>I am testing data, I am testing data.</p><p><img src=\\\"https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943\\\"></p>\",\n" +
                "        \"forecast\": 49.84,\n" +
                "        \"importance\": 2,\n" +
                "        \"type\": \"JP\",\n" +
                "        \"status\": \"published\",\n" +
                "        \"display_time\": \"2004-12-04 12:34:37\",\n" +
                "        \"comment_disabled\": true,\n" +
                "        \"pageviews\": 328,\n" +
                "        \"image_uri\": \"https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3\",\n" +
                "        \"platforms\": [\n" +
                "          \"a-platform\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 11,\n" +
                "        \"timestamp\": 62916477930,\n" +
                "        \"author\": \"Larry\",\n" +
                "        \"reviewer\": \"Charles\",\n" +
                "        \"title\": \"Qyssdjon Cdds Lqrvrijoc Gnmmkauf Xovojsmrfu Ynly Cpjwsyi\",\n" +
                "        \"content_short\": \"mock data\",\n" +
                "        \"content\": \"<p>I am testing data, I am testing data.</p><p><img src=\\\"https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943\\\"></p>\",\n" +
                "        \"forecast\": 30.24,\n" +
                "        \"importance\": 3,\n" +
                "        \"type\": \"US\",\n" +
                "        \"status\": \"deleted\",\n" +
                "        \"display_time\": \"1971-03-02 22:06:34\",\n" +
                "        \"comment_disabled\": true,\n" +
                "        \"pageviews\": 4907,\n" +
                "        \"image_uri\": \"https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3\",\n" +
                "        \"platforms\": [\n" +
                "          \"a-platform\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 12,\n" +
                "        \"timestamp\": 139534731998,\n" +
                "        \"author\": \"Elizabeth\",\n" +
                "        \"reviewer\": \"Betty\",\n" +
                "        \"title\": \"Lxnqsxk Lkbo Wgsmynq Lmcse Vkyrk Pcaxgn Hklwbbyvb\",\n" +
                "        \"content_short\": \"mock data\",\n" +
                "        \"content\": \"<p>I am testing data, I am testing data.</p><p><img src=\\\"https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943\\\"></p>\",\n" +
                "        \"forecast\": 72.48,\n" +
                "        \"importance\": 1,\n" +
                "        \"type\": \"US\",\n" +
                "        \"status\": \"published\",\n" +
                "        \"display_time\": \"1972-11-10 16:19:59\",\n" +
                "        \"comment_disabled\": true,\n" +
                "        \"pageviews\": 3756,\n" +
                "        \"image_uri\": \"https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3\",\n" +
                "        \"platforms\": [\n" +
                "          \"a-platform\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 13,\n" +
                "        \"timestamp\": 250585580775,\n" +
                "        \"author\": \"Amy\",\n" +
                "        \"reviewer\": \"Barbara\",\n" +
                "        \"title\": \"Dnlskvsw Botgrb Cqmoyb Mooncvpi Yibea Ufwnta Birfovfde Dpex\",\n" +
                "        \"content_short\": \"mock data\",\n" +
                "        \"content\": \"<p>I am testing data, I am testing data.</p><p><img src=\\\"https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943\\\"></p>\",\n" +
                "        \"forecast\": 43.72,\n" +
                "        \"importance\": 3,\n" +
                "        \"type\": \"CN\",\n" +
                "        \"status\": \"deleted\",\n" +
                "        \"display_time\": \"1992-06-05 22:05:15\",\n" +
                "        \"comment_disabled\": true,\n" +
                "        \"pageviews\": 3547,\n" +
                "        \"image_uri\": \"https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3\",\n" +
                "        \"platforms\": [\n" +
                "          \"a-platform\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 14,\n" +
                "        \"timestamp\": 574987717643,\n" +
                "        \"author\": \"Brenda\",\n" +
                "        \"reviewer\": \"Joseph\",\n" +
                "        \"title\": \"Hcxriwkq Sjcdukpm Ipibiah Vhlop Caooqpjc Ikvsaek Iexhx Ljlmvw Pnrfkxncdx\",\n" +
                "        \"content_short\": \"mock data\",\n" +
                "        \"content\": \"<p>I am testing data, I am testing data.</p><p><img src=\\\"https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943\\\"></p>\",\n" +
                "        \"forecast\": 18.04,\n" +
                "        \"importance\": 3,\n" +
                "        \"type\": \"US\",\n" +
                "        \"status\": \"published\",\n" +
                "        \"display_time\": \"2007-10-08 01:58:26\",\n" +
                "        \"comment_disabled\": true,\n" +
                "        \"pageviews\": 2226,\n" +
                "        \"image_uri\": \"https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3\",\n" +
                "        \"platforms\": [\n" +
                "          \"a-platform\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 15,\n" +
                "        \"timestamp\": 1475408277647,\n" +
                "        \"author\": \"Helen\",\n" +
                "        \"reviewer\": \"Donna\",\n" +
                "        \"title\": \"Ocwzusxqy Uebxthkjjv Qnpmyggw Pdss Rbslwhq Sxhlkpb Abejlwth Veffssg\",\n" +
                "        \"content_short\": \"mock data\",\n" +
                "        \"content\": \"<p>I am testing data, I am testing data.</p><p><img src=\\\"https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943\\\"></p>\",\n" +
                "        \"forecast\": 78.33,\n" +
                "        \"importance\": 1,\n" +
                "        \"type\": \"JP\",\n" +
                "        \"status\": \"deleted\",\n" +
                "        \"display_time\": \"1991-07-14 03:19:43\",\n" +
                "        \"comment_disabled\": true,\n" +
                "        \"pageviews\": 2636,\n" +
                "        \"image_uri\": \"https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3\",\n" +
                "        \"platforms\": [\n" +
                "          \"a-platform\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 16,\n" +
                "        \"timestamp\": 466578156561,\n" +
                "        \"author\": \"William\",\n" +
                "        \"reviewer\": \"Larry\",\n" +
                "        \"title\": \"Rosccbbc Aleum Kippbm Agziwhpmq Jshlluq Gqorj\",\n" +
                "        \"content_short\": \"mock data\",\n" +
                "        \"content\": \"<p>I am testing data, I am testing data.</p><p><img src=\\\"https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943\\\"></p>\",\n" +
                "        \"forecast\": 17.18,\n" +
                "        \"importance\": 1,\n" +
                "        \"type\": \"US\",\n" +
                "        \"status\": \"draft\",\n" +
                "        \"display_time\": \"1974-07-02 23:41:29\",\n" +
                "        \"comment_disabled\": true,\n" +
                "        \"pageviews\": 4356,\n" +
                "        \"image_uri\": \"https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3\",\n" +
                "        \"platforms\": [\n" +
                "          \"a-platform\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 17,\n" +
                "        \"timestamp\": 445597774073,\n" +
                "        \"author\": \"Ruth\",\n" +
                "        \"reviewer\": \"Melissa\",\n" +
                "        \"title\": \"Gndeun Lrfftmc Ncgfmv Cwjk Qppdtqoh Eyj Muo Ivizusro\",\n" +
                "        \"content_short\": \"mock data\",\n" +
                "        \"content\": \"<p>I am testing data, I am testing data.</p><p><img src=\\\"https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943\\\"></p>\",\n" +
                "        \"forecast\": 84.47,\n" +
                "        \"importance\": 3,\n" +
                "        \"type\": \"CN\",\n" +
                "        \"status\": \"draft\",\n" +
                "        \"display_time\": \"1979-01-10 05:46:37\",\n" +
                "        \"comment_disabled\": true,\n" +
                "        \"pageviews\": 3638,\n" +
                "        \"image_uri\": \"https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3\",\n" +
                "        \"platforms\": [\n" +
                "          \"a-platform\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 18,\n" +
                "        \"timestamp\": 177699345001,\n" +
                "        \"author\": \"Patricia\",\n" +
                "        \"reviewer\": \"Gary\",\n" +
                "        \"title\": \"Vwesj Denwcho Tsqjapx Isnhfpdcm Zqmjlqkqi Idsgmd Sokfp Pqeeyqlq\",\n" +
                "        \"content_short\": \"mock data\",\n" +
                "        \"content\": \"<p>I am testing data, I am testing data.</p><p><img src=\\\"https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943\\\"></p>\",\n" +
                "        \"forecast\": 30.17,\n" +
                "        \"importance\": 1,\n" +
                "        \"type\": \"CN\",\n" +
                "        \"status\": \"published\",\n" +
                "        \"display_time\": \"1982-03-01 16:05:47\",\n" +
                "        \"comment_disabled\": true,\n" +
                "        \"pageviews\": 888,\n" +
                "        \"image_uri\": \"https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3\",\n" +
                "        \"platforms\": [\n" +
                "          \"a-platform\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 19,\n" +
                "        \"timestamp\": 955594534174,\n" +
                "        \"author\": \"Thomas\",\n" +
                "        \"reviewer\": \"Sharon\",\n" +
                "        \"title\": \"Ilcjkfz Wvoqroby Tovbhjhpws Yerdb Ifhysnxi Toi Wexycjoq\",\n" +
                "        \"content_short\": \"mock data\",\n" +
                "        \"content\": \"<p>I am testing data, I am testing data.</p><p><img src=\\\"https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943\\\"></p>\",\n" +
                "        \"forecast\": 90.13,\n" +
                "        \"importance\": 3,\n" +
                "        \"type\": \"CN\",\n" +
                "        \"status\": \"published\",\n" +
                "        \"display_time\": \"2002-08-20 04:32:55\",\n" +
                "        \"comment_disabled\": true,\n" +
                "        \"pageviews\": 3713,\n" +
                "        \"image_uri\": \"https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3\",\n" +
                "        \"platforms\": [\n" +
                "          \"a-platform\"\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 20,\n" +
                "        \"timestamp\": 127691191264,\n" +
                "        \"author\": \"Elizabeth\",\n" +
                "        \"reviewer\": \"Maria\",\n" +
                "        \"title\": \"Tjgp Jigrtkiq Wusdqxfj Mkebgqwwz Jqtauj Mjrnkwmtwg Ilmpmryhmd Wdkm Wbyelb Gvwtqvw\",\n" +
                "        \"content_short\": \"mock data\",\n" +
                "        \"content\": \"<p>I am testing data, I am testing data.</p><p><img src=\\\"https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943\\\"></p>\",\n" +
                "        \"forecast\": 4.97,\n" +
                "        \"importance\": 2,\n" +
                "        \"type\": \"EU\",\n" +
                "        \"status\": \"draft\",\n" +
                "        \"display_time\": \"1990-12-23 06:13:00\",\n" +
                "        \"comment_disabled\": true,\n" +
                "        \"pageviews\": 4178,\n" +
                "        \"image_uri\": \"https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3\",\n" +
                "        \"platforms\": [\n" +
                "          \"a-platform\"\n" +
                "        ]\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";

    }
}
