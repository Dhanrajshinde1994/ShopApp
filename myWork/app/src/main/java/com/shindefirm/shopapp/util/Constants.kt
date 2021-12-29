package com.shindefirm.shopapp.util

import java.text.SimpleDateFormat
import java.util.*

object Constants {
    const val LOG_TAG = "NCP_Youth =="
    const val GOOGLE_PLACE_API_KEY = "AIzaSyAkNBALkBX7trFQFCrcHO2I85Re2MmzTo8"
    var BearerToken =
        "AAAAAAAAAAAAAAAAAAAAAEWbSAEAAAAAPFYIorirtcUkrEkmaTzB%2BoofyLw%3DoBrR5glY0rTnYHJpCZzTqu5KOGlptZ1zbmXQO1NuCw9tYqMq32"
    const val DEFAULT_LANGUAGE_LOCALE = "EN"
    const val MARATHI_LANGUAGE_LOCALE = "MR"
    const val HINDI_LANGUAGE_LOCALE = "HI"
    const val COUNTRY = "IN"
    const val LATITUDE = "LATITUDE"
    const val LONGITUDE = "LONGITUDE"
    const val MAP_ADDRESS = "MAP_ADDRESS"
    const val MAP_CITY = "MAP_CITY"
    const val BUNDLE_VIDEO_URL = "videoURL"
    const val REQUEST_CODE_CHOOSE = 101
    const val WITHOUT_COMPRESS_IMAGE = 100
    const val COMPRESS_30_PERCENT_IMAGE = 30
    const val COMPRESS_60_PERCENT_IMAGE = 60
    const val COMPRESS_80_PERCENT_IMAGE = 80
    const val SIZE_1_KB = 1024
    const val SIZE_1_MB = 1024 * SIZE_1_KB
    const val FILE_PROVIDER = "com.stpl.ncp_youth.fileprovider"
    const val FILE_PROVIDER_DIRECTORY = "Pictures"
    const val NCP_VIDEO_DIRECTORY = "ncp_media/ncp_video"
    const val NCP_IMAGES_DIRECTORY = "ncp_media/ncp_images"
    const val NCP_AUDIO_DIRECTORY = "ncp_media/ncp_audio"
    const val NCP_DOCUMENT_DIRECTORY = "ncp_media/ncp_document"

    //    public static final String TWITTER_API_KEY = "V6Z7KATuawk7Pe3CmN7R4rKWv";
    //    public static final String TWITTER_API_SECRET_KEY = "iESAVLf1MzBVwIEQcueFG5G0slLYkqGzEBjIKz8lYjwEbAjQJM";
    const val TWITTER_API_KEY = "jhgUFZpEPBUq2bosxpNcNmt52"
    const val TWITTER_API_SECRET_KEY = "2IILkYX1l2tJ4RbZYe3MNTfUEQnfg9IQAXS2gitjFCCJ7QWB5C"
    const val ONE_SECOND = 1000
    const val ONE_MINUTE = 60 * ONE_SECOND
    const val ONE_HOUR = 60 * ONE_MINUTE
    const val ONE_DAY = 24 * ONE_HOUR
    const val REQUEST_TIME_OUT = 50 * ONE_SECOND
    var IMG_PREVIEW_TAG = 0

    // Date
    val ddMMyyyy = SimpleDateFormat(
        "dd/MM/yyyy",
        Locale(DEFAULT_LANGUAGE_LOCALE, COUNTRY)
    )
    val hhmmaa = SimpleDateFormat(
        "hh:mm aa",
        Locale(DEFAULT_LANGUAGE_LOCALE, COUNTRY)
    )
    val ddMMyyyyhhmmaa = SimpleDateFormat(
        "dd/MM/yyyy hh:mm aa",
        Locale(DEFAULT_LANGUAGE_LOCALE, COUNTRY)
    )
    val dd_MM_yyyy_hhmmaa = SimpleDateFormat(
        "dd-MM-yyyy hh:mm aa",
        Locale(DEFAULT_LANGUAGE_LOCALE, COUNTRY)
    )
    val ddMMMyyyyhhmmaa = SimpleDateFormat(
        "dd MMM, yyyy hh:mm aa",
        Locale(DEFAULT_LANGUAGE_LOCALE, COUNTRY)
    )
    val dd_MM_yyyy_hh_mm_ss_aa = SimpleDateFormat(
        "dd_MM_yyyy_hh_mm_ss_aa",
        Locale(DEFAULT_LANGUAGE_LOCALE, COUNTRY)
    )
    val ddMMM = SimpleDateFormat(
        "dd MMM",
        Locale(DEFAULT_LANGUAGE_LOCALE, COUNTRY)
    )
    val ddMMMyyyy = SimpleDateFormat(
        "dd MMM, yyyy",
        Locale(DEFAULT_LANGUAGE_LOCALE, COUNTRY)
    )

    // Base URL
    const val MAIN_URL = "http://ncpservice.eanifarm.com/Service.asmx/" // TEST URL

    // public static final String MAIN_URL = "http://ncpservice.ncpyouths.com/Service.asmx/";// LIVE URL
    const val TWEET_MAIN_URL = "https://publish.twitter.com/oembed?url="
    const val TWEET_SUB_URL = "https://twitter.com/Interior/status/"
    const val TWEET_PostIDUrl = "https://api.twitter.com/2/users/"

    //    public static final String MAIN_URL = "http://ncpservice.erpguru.in/service.asmx/";   // TEST URL
    //   public static final String NEWS_MAIN_URL = "http://service.frontpage.ind.in/Service.asmx/";
    // Remaining web services
    const val GET_OTP = "GetUserKey_1_0?"
    const val GET_RESEND_OTP = "ResendOTP?"
    const val GET_DISTRICT = "GetDistrict?"
    const val GET_TALUKA = "GetTaluka?"
    const val GET_VILLAGE = "GetVillage?"
    const val GET_CITY = "GetCity_1_0?"
    const val GET_CONSTITUENCY = "Get_ConstituencyDetails"
    const val GET_GENERAL_DATA = "GetGeneralData_1_0?"
    const val DO_LOGIN_OR_REGISTER = "InsertUser_1_0"
    const val GET_KARYAKARTA = "GetKaryakarnidata_1_0?"
    const val INSERT_FEEDBACK = "InsertFeedback_1_0"
    const val POST_UPDATE_PROFILE = "UpdateUserProfile_1_0?"
    const val POST_UPDATE_PROFILE_PHOTO = "UpdateProfilePhoto_ById_1_0?"

    //Political Work Activity
    const val GET_PROGRAM_LIST = "GetProgramList_1_0?"
    const val GET_ACTIVITY_DATA_BY_USER_ID = "GetActivityData_ByUserId_1_0?"
    const val GET_ACTIVITY_DATA_BY_ID = "GetActivityData_ById_1_0?"
    const val POST_INSERT_ACTIVITY = "InsertActivity_1_0"
    const val POST_INSERT_FORWARD_ACTIVITY = "InsertForwardedActivity_1_0"
    const val POST_UPLOAD_ACTIVITY_PHOTO = "Upload_Activity_Photo_1_0"
    const val POST_UPLOAD_FORWARD_ACTIVITY_PHOTO = "Upload_ForwardedActivity_Photo_1_0"
    const val POST_DELETE_ACTIVITY_PHOTO = "DeletePhotoActivity_1_0"
    const val POST_DELETE_FORWARD_ACTIVITY_PHOTO = "DeletePhotoForwardedActivity_1_0"
    const val POST_DELETE_ACTIVITY = "DeleteActivity_1_0"
    const val POST_FORWARDED_ACTIVITY = "GetForwaredeActivityData_ByUserId_1_0?"
    const val POST_NOTIFICATION_ACTIVITY = "GetNotificationData_ByUserId_1_0"
    const val GET_LIKES = "GetLikesList_1_0?"
    const val INSERT_LIKES = "InsertLikes_1_0"
    const val GET_COMMENTS = "GetCommentsList_1_0?"
    const val INSERT_COMMENTS = "Insert_Comments_1_0"
    const val DELETE_COMMENTS = "DeleteComments_1_0?"
    const val REMOVE_UPDATE_PROFILE = "Sp_UpdateProfilePhoto_ById_1_0"
    const val GET_TWITTER = "Get_Dashboard_Twitter?"
    const val GET_CHAT_MESSAGE = "Get_HelpMe_Chat_Message?"
    const val GET_CHAT_MESSAGE_BY_GROUP_ID = "Get_HelpMe_Chat_MessagebyGroupId?"
    const val GET_ALL_GROUP_OF_CHAT = "Get_ReceiverChatList?"
    const val POST_INSERT_CHAT_MESSAGE = "Insert_HelpMe_Chat_Message?"
    const val POST_MARKED_AS_READ_ALL_MESSAGE = "Insert_HelpMe_tblchatreceivedstatus?"
    const val POST_UPLOAD_MEDIA_IN_CHAT = "Upload_HelpMe_Chat_Media_Message?"
    const val INSERT_SHARE_ACTIVITY_USAGE = "Insert_Share_Activityusage_1_0"
    const val GET_SLIDER_IMAGES = "Mob_GetSliderImages?"
}