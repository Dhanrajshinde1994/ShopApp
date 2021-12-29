package com.shindefirm.shopapp.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constants {
    public static final String LOG_TAG = "NCP_Youth ==";
    public static final String GOOGLE_PLACE_API_KEY = "AIzaSyAkNBALkBX7trFQFCrcHO2I85Re2MmzTo8";
    public static String BearerToken = "AAAAAAAAAAAAAAAAAAAAAEWbSAEAAAAAPFYIorirtcUkrEkmaTzB%2BoofyLw%3DoBrR5glY0rTnYHJpCZzTqu5KOGlptZ1zbmXQO1NuCw9tYqMq32";
    public static final String DEFAULT_LANGUAGE_LOCALE = "EN";
    public static final String MARATHI_LANGUAGE_LOCALE = "MR";
    public static final String HINDI_LANGUAGE_LOCALE = "HI";
    public static final String COUNTRY = "IN";
    public static final String LATITUDE = "LATITUDE";
    public static final String LONGITUDE = "LONGITUDE";
    public static final String MAP_ADDRESS = "MAP_ADDRESS";
    public static final String MAP_CITY = "MAP_CITY";
    public static final String BUNDLE_VIDEO_URL = "videoURL";
    public static final int REQUEST_CODE_CHOOSE = 101;
    public static final int WITHOUT_COMPRESS_IMAGE = 100;
    public static final int COMPRESS_30_PERCENT_IMAGE = 30;
    public static final int COMPRESS_60_PERCENT_IMAGE = 60;
    public static final int COMPRESS_80_PERCENT_IMAGE = 80;
    public static final int SIZE_1_KB = 1024;
    public static final int SIZE_1_MB = 1024 * SIZE_1_KB;
    public static final String FILE_PROVIDER = "com.stpl.ncp_youth.fileprovider";
    public static final String FILE_PROVIDER_DIRECTORY = "Pictures";

    public static final String NCP_VIDEO_DIRECTORY = "ncp_media/ncp_video";
    public static final String NCP_IMAGES_DIRECTORY = "ncp_media/ncp_images";
    public static final String NCP_AUDIO_DIRECTORY = "ncp_media/ncp_audio";
    public static final String NCP_DOCUMENT_DIRECTORY = "ncp_media/ncp_document";

//    public static final String TWITTER_API_KEY = "V6Z7KATuawk7Pe3CmN7R4rKWv";
//    public static final String TWITTER_API_SECRET_KEY = "iESAVLf1MzBVwIEQcueFG5G0slLYkqGzEBjIKz8lYjwEbAjQJM";

    public static final String TWITTER_API_KEY = "jhgUFZpEPBUq2bosxpNcNmt52";
    public static final String TWITTER_API_SECRET_KEY = "2IILkYX1l2tJ4RbZYe3MNTfUEQnfg9IQAXS2gitjFCCJ7QWB5C";


    public static final Integer ONE_SECOND = 1000;
    public static final Integer ONE_MINUTE = 60 * ONE_SECOND;
    public static final Integer ONE_HOUR = 60 * ONE_MINUTE;
    public static final Integer ONE_DAY = 24 * ONE_HOUR;

    public static final Integer REQUEST_TIME_OUT = 50 * ONE_SECOND;
    public static Integer IMG_PREVIEW_TAG = 0;

    // Date

    public static final SimpleDateFormat ddMMyyyy = new SimpleDateFormat(
            "dd/MM/yyyy",
            new Locale(DEFAULT_LANGUAGE_LOCALE, COUNTRY));
    public static final SimpleDateFormat hhmmaa = new SimpleDateFormat(
            "hh:mm aa",
            new Locale(DEFAULT_LANGUAGE_LOCALE, COUNTRY));
    public static final SimpleDateFormat ddMMyyyyhhmmaa = new SimpleDateFormat(
            "dd/MM/yyyy hh:mm aa",
            new Locale(DEFAULT_LANGUAGE_LOCALE, COUNTRY));
    public static final SimpleDateFormat dd_MM_yyyy_hhmmaa = new SimpleDateFormat(
            "dd-MM-yyyy hh:mm aa",
            new Locale(DEFAULT_LANGUAGE_LOCALE, COUNTRY));
    public static final SimpleDateFormat ddMMMyyyyhhmmaa = new SimpleDateFormat(
            "dd MMM, yyyy hh:mm aa",
            new Locale(DEFAULT_LANGUAGE_LOCALE, COUNTRY));
    public static final SimpleDateFormat dd_MM_yyyy_hh_mm_ss_aa = new SimpleDateFormat(
            "dd_MM_yyyy_hh_mm_ss_aa",
            new Locale(DEFAULT_LANGUAGE_LOCALE, COUNTRY));
    public static final SimpleDateFormat ddMMM = new SimpleDateFormat(
            "dd MMM",
            new Locale(DEFAULT_LANGUAGE_LOCALE, COUNTRY));
    public static final SimpleDateFormat ddMMMyyyy = new SimpleDateFormat(
            "dd MMM, yyyy",
            new Locale(DEFAULT_LANGUAGE_LOCALE, COUNTRY));

    // Base URL

   public static final String MAIN_URL = "http://ncpservice.eanifarm.com/Service.asmx/";// TEST URL

   // public static final String MAIN_URL = "http://ncpservice.ncpyouths.com/Service.asmx/";// LIVE URL
    public static final String TWEET_MAIN_URL = "https://publish.twitter.com/oembed?url=";
    public static final String TWEET_SUB_URL = "https://twitter.com/Interior/status/";
    public static final String TWEET_PostIDUrl = "https://api.twitter.com/2/users/";


//    public static final String MAIN_URL = "http://ncpservice.erpguru.in/service.asmx/";   // TEST URL

    //   public static final String NEWS_MAIN_URL = "http://service.frontpage.ind.in/Service.asmx/";
    // Remaining web services
    public static final String GET_OTP = "GetUserKey_1_0?";
    public static final String GET_RESEND_OTP = "ResendOTP?";
    public static final String GET_DISTRICT = "GetDistrict?";
    public static final String GET_TALUKA = "GetTaluka?";
    public static final String GET_VILLAGE = "GetVillage?";
    public static final String GET_CITY = "GetCity_1_0?";
    public static final String GET_CONSTITUENCY = "Get_ConstituencyDetails";
    public static final String GET_GENERAL_DATA = "GetGeneralData_1_0?";

    public static final String DO_LOGIN_OR_REGISTER = "InsertUser_1_0";

    public static final String GET_KARYAKARTA = "GetKaryakarnidata_1_0?";
    public static final String INSERT_FEEDBACK = "InsertFeedback_1_0";

    public static final String POST_UPDATE_PROFILE = "UpdateUserProfile_1_0?";
    public static final String POST_UPDATE_PROFILE_PHOTO = "UpdateProfilePhoto_ById_1_0?";
    //Political Work Activity
    public static final String GET_PROGRAM_LIST = "GetProgramList_1_0?";
    public static final String GET_ACTIVITY_DATA_BY_USER_ID = "GetActivityData_ByUserId_1_0?";
    public static final String GET_ACTIVITY_DATA_BY_ID = "GetActivityData_ById_1_0?";
    public static final String POST_INSERT_ACTIVITY = "InsertActivity_1_0";
    public static final String POST_INSERT_FORWARD_ACTIVITY = "InsertForwardedActivity_1_0";
    public static final String POST_UPLOAD_ACTIVITY_PHOTO = "Upload_Activity_Photo_1_0";
    public static final String POST_UPLOAD_FORWARD_ACTIVITY_PHOTO = "Upload_ForwardedActivity_Photo_1_0";
    public static final String POST_DELETE_ACTIVITY_PHOTO = "DeletePhotoActivity_1_0";
    public static final String POST_DELETE_FORWARD_ACTIVITY_PHOTO = "DeletePhotoForwardedActivity_1_0";
    public static final String POST_DELETE_ACTIVITY = "DeleteActivity_1_0";

    public static final String POST_FORWARDED_ACTIVITY = "GetForwaredeActivityData_ByUserId_1_0?";

    public static final String POST_NOTIFICATION_ACTIVITY = "GetNotificationData_ByUserId_1_0";

    public static final String GET_LIKES = "GetLikesList_1_0?";
    public static final String INSERT_LIKES = "InsertLikes_1_0";
    public static final String GET_COMMENTS = "GetCommentsList_1_0?";
    public static final String INSERT_COMMENTS = "Insert_Comments_1_0";
    public static final String DELETE_COMMENTS = "DeleteComments_1_0?";
    public static final String REMOVE_UPDATE_PROFILE = "Sp_UpdateProfilePhoto_ById_1_0";

    public static final String GET_TWITTER = "Get_Dashboard_Twitter?";
    public static final String GET_CHAT_MESSAGE = "Get_HelpMe_Chat_Message?";
    public static final String GET_CHAT_MESSAGE_BY_GROUP_ID = "Get_HelpMe_Chat_MessagebyGroupId?";
    public static final String GET_ALL_GROUP_OF_CHAT = "Get_ReceiverChatList?";
    public static final String POST_INSERT_CHAT_MESSAGE = "Insert_HelpMe_Chat_Message?";
    public static final String POST_MARKED_AS_READ_ALL_MESSAGE = "Insert_HelpMe_tblchatreceivedstatus?";
    public static final String POST_UPLOAD_MEDIA_IN_CHAT = "Upload_HelpMe_Chat_Media_Message?";

    public static final String INSERT_SHARE_ACTIVITY_USAGE = "Insert_Share_Activityusage_1_0";

    public static final String GET_SLIDER_IMAGES = "Mob_GetSliderImages?";

}