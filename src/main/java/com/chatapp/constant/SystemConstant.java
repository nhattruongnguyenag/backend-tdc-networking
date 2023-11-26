package com.chatapp.constant;

public class SystemConstant {
    public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "http://localhost:3000";
    public static final String FILE_PATH_ORIGIN = "D:/uploading-file/";
    public static final String RESET_PASSWORD_URL = "http://localhost:3000/thay-doi-mat-khau/";
    public static final String AUTHEN_REGISTER__URL = "http://localhost:3000/xac-thuc-tai-khoan/";
    public static final String HOST_PATH = "http://69.197.134.101:8080/";
    public static final String EMAIL_TDC_SOCIAL_NETWORK = "sonthu3333@gmail.com";
    public static final String EMAIL_RESET_TEXT(String url ,  String email) {
        return 
        "<table align='center' border='0' class='m_6314762921051284015content' cellpadding='0' cellspacing='0' style='background-color:#ffffff;width:500px' width='500' bgcolor='#ffffff'> <tbody> <tr> <td align='center'> <a href='https://www.netflix.com/browse?g=c1389a74-0d69-4eb7-a944-6668f5b2a088&amp;lkid=URL_LOGO&amp;lnktrk=EVO' style='color:inherit' target='_blank' data-saferedirecturl='https://www.google.com/url?q=https://www.netflix.com/browse?g%3Dc1389a74-0d69-4eb7-a944-6668f5b2a088%26lkid%3DURL_LOGO%26lnktrk%3DEVO&amp;source=gmail&amp;ust=1700660446086000&amp;usg=AOvVaw0POVaDYwWm2RuUw2KP91HJ'> <table class='m_6314762921051284015image' width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td class='m_6314762921051284015cell m_6314762921051284015content-padding' align='left' style='padding-left:40px;padding-right:40px;padding-top:20px'> <img src='"
        + HOST_PATH +
        "api/images/logo.jpg' alt='Netflix' width='70' border='0' style='border:none;outline:none;border-collapse:collapse;display:block;border-style:none' class='CToWUd' data-bit='iit'> </td> </tr> </tbody> </table> </a> <table align='left' width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td align='left' class='m_6314762921051284015content-padding' style='padding-left:40px;padding-right:40px;font-family:NetflixSans-Bold,Helvetica,Roboto,Segoe UI,sans-serif;font-weight:700;font-size:36px;line-height:43px;letter-spacing:-1px;padding-top:20px;color:#221f1f'> Thay Đổi Mật Khẩu</td> </tr> </tbody> </table> <table align='left' width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td align='left' class='m_6314762921051284015content-padding' style='padding-left:40px;padding-right:40px;font-family:NetflixSans-Regular,Helvetica,Roboto,Segoe UI,sans-serif;font-weight:400;font-size:16px;line-height:21px;color:#221f1f;padding-top:35px'> Xin chào <span style='word-break:break-all'>" 
        + email + 
        "</span>,</td> </tr> </tbody> </table> <table align='left' width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td align='left' class='m_6314762921051284015content-padding' style='padding-left:40px;padding-right:40px;font-family:NetflixSans-Regular,Helvetica,Roboto,Segoe UI,sans-serif;font-weight:400;font-size:16px;line-height:21px;padding-top:20px;color:#221f1f'> Chúng tôi là đội ngũ hỗ trợ đến từ TDC Social Network. </td> </tr> <tr> <td align='left' class='m_6314762921051284015content-padding' style='padding-left:40px;padding-right:40px;font-family:NetflixSans-Regular,Helvetica,Roboto,Segoe UI,sans-serif;font-weight:400;font-size:16px;line-height:21px;padding-top:20px;color:#221f1f'> Bạn đã yêu cầu hệ thống chúng tôi giúp bạn thay đổi mật khẩu. </td> </tr> <tr> <td align='left' class='m_6314762921051284015content-padding' style='padding-left:40px;padding-right:40px;font-family:NetflixSans-Regular,Helvetica,Roboto,Segoe UI,sans-serif;font-weight:400;font-size:16px;line-height:21px;padding-top:20px;color:#221f1f'> Nhấn vào nút bên dưới để tiến hành thay đổi mật khẩu. </td> </tr> </tbody> </table> <table class='m_6314762921051284015single-button m_6314762921051284015mobile-100w' align='center' width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td class='m_6314762921051284015content-padding' style='padding-left:40px;padding-right:40px;padding-top:30px' align='center'> <table style='background-color:hsl(196.93deg 99.18% 47.65%);border-radius:4px;width:100%' cellpadding='0' cellspacing='0' width='100%' bgcolor='#157DEC'> <tbody> <tr> <td align='center' style='font-family:NetflixSans-Bold,Helvetica,Roboto,Segoe UI,sans-serif;font-weight:700;font-size:14px;line-height:17px;letter-spacing:-0.2px;padding-top:20px;padding:14px 40px;color:#ffffff'> <a href='"
        + url +
        "' style='font-family:NetflixSans-Bold,Helvetica,Roboto,Segoe UI,sans-serif;font-weight:700;font-size:14px;line-height:17px;letter-spacing:-0.2px;text-align:center;text-decoration:none;display:block;color:#ffffff' target='_blank' data-saferedirecturl='https://www.google.com/url?q=https://www.netflix.com/upgrade?g%3Dc1389a74-0d69-4eb7-a944-6668f5b2a088%26lkid%3DURL_UPGRADE%26lnktrk%3DEVO%26nftoken%3DBQAbAAEBEItWK7IUpDFWJa6MdZv1zl%252BAoHHuaBrM52Mq6Q%252FbVq7zXA1xQ%252FIqKWRFanLvNrgeuptFHHuuEv1olrd6us8yrXEvVJ9VjBqgS65bHNfRcLgw2ZNEgetqthMPrZ4fLh3ZLN8TQ8libfVIxwgypcVjWzj%252BkSzuIlfHFeqCb2hBhsuUlddiB7kVF0k8nu09V7gtzXYhByuulKSkgrmH%252F6yEIjMzg%252BsocCrYOabsZ7lVkf7ZZ7I%253D&amp;source=gmail&amp;ust=1700660446086000&amp;usg=AOvVaw1T5vVbhpKzKawoq_WLVgzQ'> Thay Đổi Mật Khẩu</a> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <table width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td class='m_6314762921051284015content-padding' style='padding-left:40px;padding-right:40px;padding-top:30px'> <table align='center' width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td style='font-size:0;line-height:0;border-style:solid;border-bottom-width:0;border-color:#221f1f;border-top-width:2px'> &nbsp;</td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> <tr> <td align='center' class='m_6314762921051284015footer-shell' style='background-color:#ffffff' bgcolor='#ffffff'> <table class='m_6314762921051284015footer' width='100%' border='0' cellpadding='0' cellspacing='0'> <tbody> <tr> <td align='center' valign='top' class='m_6314762921051284015footer-shell m_6314762921051284015content-padding' style='padding-left:40px;padding-right:40px;background-color:#ffffff' bgcolor='#ffffff'> <table width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td style='font-size:0;line-height:0;height:40px' height='40'>&nbsp; </td> </tr> </tbody> </table> <table width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td valign='top' style='padding:0 20px 0 0'> <table class='m_6314762921051284015component-image m_6314762921051284015image' width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td class='m_6314762921051284015cell m_6314762921051284015component-image' align='center' style='padding-top:0'><img src='" 
        + HOST_PATH +
        "api/images/logo.jpg' alt='' width='70' border='0' style='border:none;outline:none;border-collapse:collapse;display:block' class='CToWUd' data-bit='iit'></td> </tr> </tbody> </table> </td> <td valign='top'> <table width='100%' valign='top' cellpadding='0' cellspacing='0'> <tbody> <tr> <td> <table align='left' width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td align='left' style='font-family:NetflixSans-Regular,Helvetica,Roboto,Segoe UI,sans-serif;font-weight:400;font-size:14px;line-height:18px;letter-spacing:-0.25px;color:#a4a4a4;padding-top:0'> <span>Nếu có câu hỏi? Hãy gửi mail đến <a href='#' style='text-decoration:underline;color:#a4a4a4' target='_blank' data-saferedirecturl='https://www.google.com/url?q=https://help.netflix.com/help?g%3Dc1389a74-0d69-4eb7-a944-6668f5b2a088%26lkid%3DURL_HELP_QUESTIONS%26lnktrk%3DEVO&amp;source=gmail&amp;ust=1700660446087000&amp;usg=AOvVaw0Jw1ySUk3m3jzXnNpvLXQw'><span> tdc.social.center@gmail.com</span></a></span> </td> </tr> </tbody> </table> <table align='left' width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td align='left' style='font-family:NetflixSans-Regular,Helvetica,Roboto,Segoe UI,sans-serif;font-weight:400;font-size:11px;line-height:14px;letter-spacing:-0.1px;padding-top:20px;color:#a4a4a4'> Đây là tin nhắn được gửi tới <a href='https://www.netflix.com/browse?g=c1389a74-0d69-4eb7-a944-6668f5b2a088&amp;lkid=URL_EMAIL&amp;lnktrk=EVO' class='m_6314762921051284015hide-link' style='color:#a4a4a4;text-decoration:none' target='_blank' data-saferedirecturl='https://www.google.com/url?q=https://www.netflix.com/browse?g%3Dc1389a74-0d69-4eb7-a944-6668f5b2a088%26lkid%3DURL_EMAIL%26lnktrk%3DEVO&amp;source=gmail&amp;ust=1700660446087000&amp;usg=AOvVaw14ialWuMQEBFti7lBKr6qP'>[" 
        + email +
        "]</a> by TDC Social Network <br> <a href='https://www.netflix.com/browse?g=c1389a74-0d69-4eb7-a944-6668f5b2a088&amp;lkid=URL_SRC&amp;lnktrk=EVO' class='m_6314762921051284015hide-link' style='color:#a4a4a4;text-decoration:none' target='_blank' data-saferedirecturl='https://www.google.com/url?q=https://www.netflix.com/browse?g%3Dc1389a74-0d69-4eb7-a944-6668f5b2a088%26lkid%3DURL_SRC%26lnktrk%3DEVO&amp;source=gmail&amp;ust=1700660446087000&amp;usg=AOvVaw0-0FcCTDpHY9DKyjg4LAuj'>@TDC Social Network , Thủ Đức , Thành Phố Hồ Chí Minh </a> </td> </tr> </tbody> </table> <table width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td style='font-size:0;line-height:0;height:40px' height='40'>&nbsp;</td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody></table>";
    }

    public static final String EMAIL_AUTHEN_REGISTER_TEXT(String url ,  String email) {
        return 
        "<table align='center' border='0' class='m_6314762921051284015content' cellpadding='0' cellspacing='0' style='background-color:#ffffff;width:500px' width='500' bgcolor='#ffffff'> <tbody> <tr> <td align='center'> <a href='https://www.netflix.com/browse?g=c1389a74-0d69-4eb7-a944-6668f5b2a088&amp;lkid=URL_LOGO&amp;lnktrk=EVO' style='color:inherit' target='_blank' data-saferedirecturl='https://www.google.com/url?q=https://www.netflix.com/browse?g%3Dc1389a74-0d69-4eb7-a944-6668f5b2a088%26lkid%3DURL_LOGO%26lnktrk%3DEVO&amp;source=gmail&amp;ust=1700660446086000&amp;usg=AOvVaw0POVaDYwWm2RuUw2KP91HJ'> <table class='m_6314762921051284015image' width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td class='m_6314762921051284015cell m_6314762921051284015content-padding' align='left' style='padding-left:40px;padding-right:40px;padding-top:20px'> <img src='"
        + HOST_PATH +
        "api/images/logo.jpg' alt='Netflix' width='70' border='0' style='border:none;outline:none;border-collapse:collapse;display:block;border-style:none' class='CToWUd' data-bit='iit'> </td> </tr> </tbody> </table> </a> <table align='left' width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td align='left' class='m_6314762921051284015content-padding' style='padding-left:40px;padding-right:40px;font-family:NetflixSans-Bold,Helvetica,Roboto,Segoe UI,sans-serif;font-weight:700;font-size:36px;line-height:43px;letter-spacing:-1px;padding-top:20px;color:#221f1f'> Xác Thực Đăng Ký Tài Khoản</td> </tr> </tbody> </table> <table align='left' width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td align='left' class='m_6314762921051284015content-padding' style='padding-left:40px;padding-right:40px;font-family:NetflixSans-Regular,Helvetica,Roboto,Segoe UI,sans-serif;font-weight:400;font-size:16px;line-height:21px;color:#221f1f;padding-top:35px'> Xin chào <span style='word-break:break-all'>" 
        + email + 
        "</span>,</td> </tr> </tbody> </table> <table align='left' width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td align='left' class='m_6314762921051284015content-padding' style='padding-left:40px;padding-right:40px;font-family:NetflixSans-Regular,Helvetica,Roboto,Segoe UI,sans-serif;font-weight:400;font-size:16px;line-height:21px;padding-top:20px;color:#221f1f'> Chúng tôi là đội ngũ hỗ trợ đến từ TDC Social Network. </td> </tr> <tr> <td align='left' class='m_6314762921051284015content-padding' style='padding-left:40px;padding-right:40px;font-family:NetflixSans-Regular,Helvetica,Roboto,Segoe UI,sans-serif;font-weight:400;font-size:16px;line-height:21px;padding-top:20px;color:#221f1f'> Bạn đã yêu cầu hệ thống chúng tôi giúp bạn xác thực để đăng ký tài khoản. </td> </tr> <tr> <td align='left' class='m_6314762921051284015content-padding' style='padding-left:40px;padding-right:40px;font-family:NetflixSans-Regular,Helvetica,Roboto,Segoe UI,sans-serif;font-weight:400;font-size:16px;line-height:21px;padding-top:20px;color:#221f1f'> Nhấn vào nút bên dưới để tiến hành xác thực tài khoản. </td> </tr> </tbody> </table> <table class='m_6314762921051284015single-button m_6314762921051284015mobile-100w' align='center' width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td class='m_6314762921051284015content-padding' style='padding-left:40px;padding-right:40px;padding-top:30px' align='center'> <table style='background-color:hsl(196.93deg 99.18% 47.65%);border-radius:4px;width:100%' cellpadding='0' cellspacing='0' width='100%' bgcolor='#157DEC'> <tbody> <tr> <td align='center' style='font-family:NetflixSans-Bold,Helvetica,Roboto,Segoe UI,sans-serif;font-weight:700;font-size:14px;line-height:17px;letter-spacing:-0.2px;padding-top:20px;padding:14px 40px;color:#ffffff'> <a href='"
        + url +
        "' style='font-family:NetflixSans-Bold,Helvetica,Roboto,Segoe UI,sans-serif;font-weight:700;font-size:14px;line-height:17px;letter-spacing:-0.2px;text-align:center;text-decoration:none;display:block;color:#ffffff' target='_blank' data-saferedirecturl='https://www.google.com/url?q=https://www.netflix.com/upgrade?g%3Dc1389a74-0d69-4eb7-a944-6668f5b2a088%26lkid%3DURL_UPGRADE%26lnktrk%3DEVO%26nftoken%3DBQAbAAEBEItWK7IUpDFWJa6MdZv1zl%252BAoHHuaBrM52Mq6Q%252FbVq7zXA1xQ%252FIqKWRFanLvNrgeuptFHHuuEv1olrd6us8yrXEvVJ9VjBqgS65bHNfRcLgw2ZNEgetqthMPrZ4fLh3ZLN8TQ8libfVIxwgypcVjWzj%252BkSzuIlfHFeqCb2hBhsuUlddiB7kVF0k8nu09V7gtzXYhByuulKSkgrmH%252F6yEIjMzg%252BsocCrYOabsZ7lVkf7ZZ7I%253D&amp;source=gmail&amp;ust=1700660446086000&amp;usg=AOvVaw1T5vVbhpKzKawoq_WLVgzQ'> Xác Thực Email</a> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <table width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td class='m_6314762921051284015content-padding' style='padding-left:40px;padding-right:40px;padding-top:30px'> <table align='center' width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td style='font-size:0;line-height:0;border-style:solid;border-bottom-width:0;border-color:#221f1f;border-top-width:2px'> &nbsp;</td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> <tr> <td align='center' class='m_6314762921051284015footer-shell' style='background-color:#ffffff' bgcolor='#ffffff'> <table class='m_6314762921051284015footer' width='100%' border='0' cellpadding='0' cellspacing='0'> <tbody> <tr> <td align='center' valign='top' class='m_6314762921051284015footer-shell m_6314762921051284015content-padding' style='padding-left:40px;padding-right:40px;background-color:#ffffff' bgcolor='#ffffff'> <table width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td style='font-size:0;line-height:0;height:40px' height='40'>&nbsp; </td> </tr> </tbody> </table> <table width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td valign='top' style='padding:0 20px 0 0'> <table class='m_6314762921051284015component-image m_6314762921051284015image' width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td class='m_6314762921051284015cell m_6314762921051284015component-image' align='center' style='padding-top:0'><img src='"
        + HOST_PATH +
        "api/images/logo.jpg' alt='' width='70' border='0' style='border:none;outline:none;border-collapse:collapse;display:block' class='CToWUd' data-bit='iit'></td> </tr> </tbody> </table> </td> <td valign='top'> <table width='100%' valign='top' cellpadding='0' cellspacing='0'> <tbody> <tr> <td> <table align='left' width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td align='left' style='font-family:NetflixSans-Regular,Helvetica,Roboto,Segoe UI,sans-serif;font-weight:400;font-size:14px;line-height:18px;letter-spacing:-0.25px;color:#a4a4a4;padding-top:0'> <span>Nếu có câu hỏi? Hãy gửi mail đến <a href='#' style='text-decoration:underline;color:#a4a4a4' target='_blank' data-saferedirecturl='https://www.google.com/url?q=https://help.netflix.com/help?g%3Dc1389a74-0d69-4eb7-a944-6668f5b2a088%26lkid%3DURL_HELP_QUESTIONS%26lnktrk%3DEVO&amp;source=gmail&amp;ust=1700660446087000&amp;usg=AOvVaw0Jw1ySUk3m3jzXnNpvLXQw'><span> tdc.social.center@gmail.com</span></a></span> </td> </tr> </tbody> </table> <table align='left' width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td align='left' style='font-family:NetflixSans-Regular,Helvetica,Roboto,Segoe UI,sans-serif;font-weight:400;font-size:11px;line-height:14px;letter-spacing:-0.1px;padding-top:20px;color:#a4a4a4'> Đây là tin nhắn được gửi tới <a href='https://www.netflix.com/browse?g=c1389a74-0d69-4eb7-a944-6668f5b2a088&amp;lkid=URL_EMAIL&amp;lnktrk=EVO' class='m_6314762921051284015hide-link' style='color:#a4a4a4;text-decoration:none' target='_blank' data-saferedirecturl='https://www.google.com/url?q=https://www.netflix.com/browse?g%3Dc1389a74-0d69-4eb7-a944-6668f5b2a088%26lkid%3DURL_EMAIL%26lnktrk%3DEVO&amp;source=gmail&amp;ust=1700660446087000&amp;usg=AOvVaw14ialWuMQEBFti7lBKr6qP'>[" 
        + email +
        "]</a> by TDC Social Network <br> <a href='https://www.netflix.com/browse?g=c1389a74-0d69-4eb7-a944-6668f5b2a088&amp;lkid=URL_SRC&amp;lnktrk=EVO' class='m_6314762921051284015hide-link' style='color:#a4a4a4;text-decoration:none' target='_blank' data-saferedirecturl='https://www.google.com/url?q=https://www.netflix.com/browse?g%3Dc1389a74-0d69-4eb7-a944-6668f5b2a088%26lkid%3DURL_SRC%26lnktrk%3DEVO&amp;source=gmail&amp;ust=1700660446087000&amp;usg=AOvVaw0-0FcCTDpHY9DKyjg4LAuj'>@TDC Social Network , Thủ Đức , Thành Phố Hồ Chí Minh </a> </td> </tr> </tbody> </table> <table width='100%' cellpadding='0' cellspacing='0'> <tbody> <tr> <td style='font-size:0;line-height:0;height:40px' height='40'>&nbsp;</td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody></table>";
    }

}
