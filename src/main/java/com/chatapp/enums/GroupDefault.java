package com.chatapp.enums;

public enum GroupDefault {
    GROUP_TDC("","group_tdc"),
    GROUP_KET_NOI_DOANH_NGHIEP("","group_connect_business"),
    GROUP_DIEN_DIEN_TU("Khoa điện - Điện tử","group_dien_dien_tu"),
    GROUP_CONG_NGHE_THONG_TIN("Khoa Công Nghệ Thông Tin","group_cong_nghe_thong_tin"),
    GROUP_CONG_NGHE_TU_DONG("Khoa Công Nghệ Tự Động","group_cong_nghe_tu_dong"),
    GROUP_CO_KHI_CHE_TAO_MAY("Khoa Cơ Khí Chế Tạo Máy","group_co_khi_che_tao_may"),
    GROUP_CO_KHI_OTO("Khoa Cơ Khí Ô Tô","group_co_khi_che_oto"),
    GROUP_TAI_CHINH_KE_TOAN("Khoa Tài Chính - Kế Toán","group_tai_chinh_ke_toan"),
    GROUP_QUAN_TRI_KINH_DOANH("Khoa Quản Trị Kinh Doanh","group_quan_tri_kinh_doanh"),
    GROUP_DU_LICH("Khoa Du Lịch","group_du_lich"),
    GROUP_TIENG_ANH("Khoa Tiếng Anh","group_tieng_anh"),
    GROUP_TIENG_HAN("Khoa Tiếng Hàn","group_tieng_han"),
    GROUP_BO_MON_TIENG_NHAT("Khoa Bộ Môn Tiếng Nhật","group_tai_chinh_ke_toan");

    private String facultyName;
    private String codeGroup;

    GroupDefault(String facultyName, String codeGroup) {
        this.facultyName = facultyName;
        this.codeGroup = codeGroup;
    }

    public String getName() {
        return facultyName;
    }

    public String getCodeGroup() {
        return codeGroup;
    }
}
