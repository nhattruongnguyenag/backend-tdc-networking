package com.chatapp.enums;

public enum GroupDefault {
    GROUP_TDC("","group_tdc",Long.valueOf(1)),
    GROUP_KET_NOI_DOANH_NGHIEP("","group_connect_business",Long.valueOf(2)),
    GROUP_DIEN_DIEN_TU("Khoa điện - Điện tử","group_dien_dien_tu",Long.valueOf(3)),
    GROUP_CONG_NGHE_THONG_TIN("Khoa Công Nghệ Thông Tin","group_cong_nghe_thong_tin",Long.valueOf(4)),
    GROUP_CONG_NGHE_TU_DONG("Khoa Công Nghệ Tự Động","group_cong_nghe_tu_dong",Long.valueOf(5)),
    GROUP_CO_KHI_CHE_TAO_MAY("Khoa Cơ Khí Chế Tạo Máy","group_co_khi_che_tao_may",Long.valueOf(6)),
    GROUP_CO_KHI_OTO("Khoa Cơ Khí Ô Tô","group_co_khi_oto",Long.valueOf(7)),
    GROUP_TAI_CHINH_KE_TOAN("Khoa Tài Chính - Kế Toán","group_tai_chinh_ke_toan",Long.valueOf(8)),
    GROUP_QUAN_TRI_KINH_DOANH("Khoa Quản Trị Kinh Doanh","group_quan_tri_kinh_doanh",Long.valueOf(9)),
    GROUP_DU_LICH("Khoa Du Lịch","group_du_lich",Long.valueOf(10)),
    GROUP_TIENG_ANH("Khoa Tiếng Anh","group_tieng_anh",Long.valueOf(11)),
    GROUP_TIENG_HAN("Khoa Tiếng Hàn","group_tieng_han",Long.valueOf(12)),
    GROUP_BO_MON_TIENG_NHAT("Khoa Bộ Môn Tiếng Nhật","group_tai_chinh_ke_toan",Long.valueOf(13));

    private String facultyName;
    private String codeGroup;
    private Long idGroup;

    GroupDefault(String facultyName, String codeGroup, Long idGroup) {
        this.facultyName = facultyName;
        this.codeGroup = codeGroup;
        this.idGroup = idGroup;
    }

    public String getName() {
        return facultyName;
    }

    public String getCodeGroup() {
        return codeGroup;
    }

    public Long getIdGroup() {
        return idGroup;
    }
}
