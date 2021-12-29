package com.shindefirm.shopapp.web_service;






import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface ApiInterface
{

   /* @GET("getNewsDetails_Mob")
    Call<NewsDescriptionModel> getNewsDetails_Mob(@Query("NewsId") int NewsID, @Query("Userid") int Userid);


    @GET("GetNews_ByUserId_1_0")
    Call<NewsResponse> get_NewsWithAdvt_1_0(@Query("UserId") String userID, @Query("PageNo") String pageno);

    @GET("Dashboard_Activities")
    Call<RecentActivityResponse> get_Dashboard_Activities(@Query("UserId") String userID, @Query("PageNo") String pageno);

    @GET
    Call<RecentTweetResponse> get_Recent_Tweet(@Url String url);

    @GET
    Call<RecentTweetIDResponse> get_Recent_Tweet_ID(@Url String url);
*/

  /*  @GET("get_News_Notification_Data")
    Call<NewsResponse> get_News_Notification_Data(@Query("userID") String userID, @Query("NewsId") String NewsId);
*/
/*

    @GET("getVehicleCurrentLocationAndInvoice_VTS_1_0")
    Call<Master_Single_Loc_Info> getVehicleCurrentLocationAndInvoice_VTS_1_0(@Query("UserId") String UserId, @Query("VehicleNo") String VehicleNo, @Query("DeviceId") String DeviceId, @Query("VehicleId") String VehicleId);

    @GET("getGPRSDataByDate_1_0")
    Call<Device_Info> getGPRSDataByDate_1_0(@Query("VehicleNo") String VehicleNo, @Query("Date") String Date);
*/



  /*  @GET("getVehicleStatusCount_1_0")
    Call<Device_Status_Master> getVehicleStatusCount_1_0(@Query("UserId") String UserId);

    @GET("getVTSSummaryReport_1_0")
    Call<Device_Summery_Master> getVTSSummaryReport_1_0(@Query("UserId") String UserId, @Query("VehicleNo") String VehicleNo, @Query("DeviceId") String DeviceId, @Query("VehicleId") String VehicleId, @Query("FromDate") String FromDate, @Query("ToDate") String ToDate);

    @GET("getVTSOverSpeedReport_1_0")
    Call<Device_Speed_Master> getVTSOverSpeedReport_1_0(@Query("UserId") String UserId, @Query("VehicleNo") String VehicleNo, @Query("DeviceId") String DeviceId, @Query("VehicleId") String VehicleId, @Query("FromDate") String FromDate, @Query("ToDate") String ToDate, @Query("FromSpeed") String FromSpeed, @Query("ToSpeed") String ToSpeed);

    @GET("getGPRSDataByDatetime_1_0")
    Call<Device_Track_Info> getGPRSDataByDatetime_1_0(@Query("UserId") String UserId, @Query("VehicleNo") String VehicleNo, @Query("DeviceId") String DeviceId, @Query("VehicleId") String VehicleId, @Query("FromDate") String FromDate, @Query("ToDate") String ToDate);

    @GET("getInvoiceDetails_VTS_1_0")
    Call<InvoiceDetailsResponse> getInvoiceDetails_VTS_1_0(@Query("UserId") String UserId);

    @GET("getOverSpeedVehicleDetails_VTS_1_0")
    Call<OverSpeed_Vehicle_Response> getOverSpeedVehicleDetails_VTS_1_0(@Query("UserId") String UserId);

    @GET("getMaterialOrderEnquiry_VTS_1_0")
    Call<Enquiry_Master> getMaterialOrderEnquiry_VTS_1_0(@Query("UserId") String UserId);

    @GET("getPOIDetails_VTS_1_0")
    Call<PoiDetailsResponse> getPOIDetails_VTS_1_0(@Query("UserId") String UserId);

    @GET("getTripReport_1_0")
    Call<TripDetailsResponse> getTripReport_1_0(@Query("UserId") String UserId, @Query("VehicleNo") String VehicleNo, @Query("DeviceId") String DeviceId, @Query("VehicleId") String VehicleId, @Query("FromDate") String FromDate, @Query("ToDate") String ToDate);

    @GET("getDistrict_VTS_1_0")
    Call<DistrictReportMaster> getDistrict_VTS_1_0(@Query("UserId") String UserId, @Query("StateId") String StateId);

    @GET("getTaluka_VTS_1_0")
    Call<TalukaReportMaster> getTaluka_VTS_1_0(@Query("UserId") String UserId, @Query("DistrictId") String DistrictId);

    @GET("getPlotDetails_VTS_1_0")
    Call<PlotReportMaster> getPlotDetails_VTS_1_0(@Query("UserId") String UserId, @Query("DistrictId") String DistrictId, @Query("TalukaId") String TalukaId, @Query("MaterialId") String MaterialId);

    @GET("getWeeklyTripReport_VTS_1_0")
    Call<WeeklyDetailsResponse> getWeeklyTripReport_VTS_1_0(@Query("UserId") String UserId, @Query("VehicleNo") String VehicleNo, @Query("DeviceId") String DeviceId, @Query("VehicleId") String VehicleId, @Query("FromDate") String FromDate, @Query("ToDate") String ToDate);

    @GET("getPOIVehicleDetails_VTS_1_0")
    Call<Poi_vehicle_Deatils_Response> getPOIVehicleDetails_VTS_1_0(@Query("UserId") String UserId);

    @GET("getPOI_InOut_VTS_1_0")
    Call<Single_poi_details_response> getPOI_InOut_VTS_1_0(@Query("UserId") String UserId, @Query("VehicleNo") String VehicleNo, @Query("DeviceId") String DeviceId, @Query("VehicleId") String VehicleId);

    @GET("getVehicleCountDashboard_VTS_1_0")
    Call<VehicleCountResponse> getVehicleCountDashboard_VTS_1_0(@Query("UserId") String UserId);

    @GET("getOverSpeedDetails_Vehicle_VTS_1_0")
    Call<Single_Overspeed_Details_Response> getOverSpeedDetails_Vehicle_VTS_1_0(@Query("UserId") String UserId, @Query("VehicleNo") String VehicleNo, @Query("DeviceId") String DeviceId, @Query("VehicleId") String VehicleId);

    @GET("getVehiclePaymentHistory_VTS_1_0")
    Call<Vehicle_Payment_history_Response> getVehiclePaymentHistory_VTS_1_0(@Query("UserId") String UserId, @Query("FromDate") String FromDate, @Query("ToDate") String ToDate);

    @GET("getVehiclePayment_VTS_1_0")
    Call<Vehicle_Payment_Master> getVehiclePayment_VTS_1_0(@Query("UserId") String UserId, @Query("RateTypeId") String RateTypeId);

    @GET("getNearestPlotDetails_VTS_1_0")
    Call<PlotReportMaster> getNearestPlotDetails_VTS_1_0(@Query("UserId") String UserId, @Query("VehicleNo") String VehicleNo, @Query("DeviceId") String DeviceId, @Query("VehicleId") String VehicleId, @Query("MaterialId") String MaterialId, @Query("RadiusInKm") String RadiusInKm);

    @GET("getTalukaByDistrictIds_VTS_1_0")
    Call<TalukaReportMaster> getTalukaByDistrictIds_VTS_1_0(@Query("UserId") String UserId, @Query("DistrictIds") String DistrictIds);

    @GET("getTrackingAddress_VTS_1_0")
    Call<Address_Response> getTrackingAddress_VTS_1_0(@Query("UserId") String UserId, @Query("VehicleNo") String VehicleNo, @Query("DeviceId") String DeviceId, @Query("VehicleId") String VehicleId, @Query("FromDate") String FromDate, @Query("ToDate") String ToDate);

    @GET("getInvoiceHistory_VTS_1_0")
    Call<Invoice_report_Response> getInvoiceHistory_VTS_1_0(@Query("UserId") String UserId, @Query("VehicleNo") String VehicleNo, @Query("DeviceId") String DeviceId, @Query("VehicleId") String VehicleId, @Query("FromDate") String FromDate, @Query("ToDate") String ToDate);

    @GET("getUserGeneralSetting_VTS_1_0")
    Call<General_Data_Master> getUserGeneralSetting_VTS_1_0(@Query("UserId") String UserId, @Query("AppId") String AppId);

//    @GET("getServicePoint_VTS_1_0")
//    Call<ServiceStationResponse> getServicePoint_VTS_1_0(@Query("UserId") String UserId, @Query("DistrictId") String DistrictId, @Query("TalukaId") String TalukaId);


    @GET("getComplaintDetails_VTS_1_0")
    Call<Complaint_Details_Response> getComplaintDetails_VTS_1_0(@Query("UserId") String UserId);


    @GET("getComplaintChat_VTS_1_0")
    Call<Complaints_Chat_Response> getComplaintChat_VTS_1_0(@Query("UserId") String UserId, @Query("ComplaintId") String ComplaintId);

    @GET("getServicePoint_VTS_1_0")
    Call<ServiceStationResponse> getServicePoint_VTS_1_0(@Query("UserId") String UserId, @Query("DistrictId") String DistrictId, @Query("TalukaId") String TalukaId);
*/
}