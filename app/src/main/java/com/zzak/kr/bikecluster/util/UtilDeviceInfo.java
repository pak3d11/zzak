package com.compa.ikhp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;

import com.compa.ikhp.IkhpApplication;
import com.compa.ikhp.BuildConfig;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * 디바이스 관련 기능 메소드 모음 클래스
 *
 * @author IT사업부 곽성규
 * @version 1.0.0
 * @date 2018-07-09 오후 2:27
 */

public class UtilDeviceInfo {

    public static final int CAMERA = 0;

    /**
     * 현재 기기의 외부저장소가 쓰기 가능한 상태인지 확인
     *
     * @author 곽성규
     * @return
     */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * 현재 기기의 외부저장소가 읽기 가능한 상태인지 확인
     *
     * @author 곽성규
     * @return
     */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * 현재 기기의 디스플레이(해상도) 사이즈(PX) 획득 메소드
     *
     * @author 곽성규
     * @param activity
     * @return
     */
    public static Point getScreenSize(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return  size;
    }

    /**
     * 현재 기기의 디스플레이(해상도) 사이즈(PX) 획득 메소드
     * 사이즈 = Status Bar - softKey
     *
     * @author 곽성규
     * @param activity
     * @return
     */
    public static Point getRealHeightSize(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return  size;
    }


    /**
     * 현재 기기의 소프트 키 존재 유무 확인 메소드
     *
     * ex) LG Mobile
     *
     * @author 곽성규
     * @param context
     * @return
     */
    public static boolean isSoftMenu(Context context){
        boolean hasMenuKey = ViewConfiguration.get(context.getApplicationContext()).hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
        if(!hasMenuKey && !hasBackKey){
            if ((Build.BRAND.equals("SAMSUNG") || Build.BRAND.equals("samsung")) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }

    /**
     * 현재 기기의 소프트 키 크기 획득 메소드
     *
     * ex) LG Mobile
     *
     * @author 곽성규
     * @param context
     * @return
     */
    public static int getSoftMenuHeight(Context context){
        Resources resources = context.getApplicationContext().getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int deviceHeight;
        if(resourceId > 0){
            deviceHeight = resources.getDimensionPixelSize(resourceId);
            return deviceHeight;
        }
        return 0;
    }


    /**
     * 현재 기기의 디스플레이 DPI 배율 획득 메소드
     *
     * @author 곽성규
     * @param activity
     * @return
     */
    public static float getScreenDpi(Activity activity){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return (float) metrics.densityDpi;
    }
    public static float getScreenDpiRatio(Activity activity){
        return getScreenDpi(activity)/160f;
    }

    /**
     * 현재 기기의 고유한 디바이스 번호 획득 메소드
     * android.permission.READ_PHONE_STATE
     *
     * @author 곽성규
     * @param context
     * @return
     */
    public static UUID getUuid(Context context) {
        UUID uuid = null;
        try {
            String android_id = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            if(!"9774d56d682e549c".equals(android_id)){
                uuid = UUID.nameUUIDFromBytes(android_id.getBytes("utf8"));
            }else {
                String device_id = ((TelephonyManager)context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                uuid = device_id != null ? UUID.nameUUIDFromBytes(device_id.getBytes("utf8")) : UUID.randomUUID();
            }
        }catch (SecurityException e){
            e.printStackTrace();
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return uuid;
    }

    public static Map<String, String> getDeviceInfo(Context context){
        Map<String, String> info;
        try {
            info = new HashMap<>();
            info.put("MANUFACTURER", Build.MANUFACTURER);
            info.put("BRAND", Build.BRAND);
            info.put("MODEL", Build.MODEL);
            info.put("PRODUCT", Build.PRODUCT);
            info.put("DEVICE", Build.DEVICE);
            info.put("SDK_INT", ""+Build.VERSION.SDK_INT);
            info.put("ID", Build.ID);
            info.put("HOST", Build.HOST);
            info.put("SERIAL", Build.SERIAL);
            info.put("UUID", ""+getUuid(context));
            info.put("BOARD", Build.BOARD);
            info.put("BOOTLOADER", Build.BOOTLOADER);
            info.put("DISPLAY", Build.DISPLAY);
            info.put("FINGERPRINT", Build.FINGERPRINT);
            info.put("TAGS", Build.TAGS);
            info.put("TYPE", Build.TYPE);
            info.put("USER", Build.USER);
            info.put("RADIO_VERSION", Build.getRadioVersion());
//            Log.d(IkhpApplication.BASIC_TAG, "MANUFACTURER : " +  Build.MANUFACTURER);
//            Log.d(IkhpApplication.BASIC_TAG, "BRAND : " +  Build.BRAND);
//            Log.d(IkhpApplication.BASIC_TAG, "MODEL : " +  Build.MODEL);
//            Log.d(IkhpApplication.BASIC_TAG, "PRODUCT : " +  Build.PRODUCT);
//            Log.d(IkhpApplication.BASIC_TAG, "DEVICE : " +  Build.DEVICE);
//            Log.d(IkhpApplication.BASIC_TAG, "SDK_INT : " +  Build.VERSION.SDK_INT);
//            Log.d(IkhpApplication.BASIC_TAG, "ID : " +  Build.ID);
//            Log.d(IkhpApplication.BASIC_TAG, "HOST : " +  Build.HOST);
//            Log.d(IkhpApplication.BASIC_TAG, "SERIAL : " +  Build.SERIAL);
//            Log.d(IkhpApplication.BASIC_TAG, "UUID : " +  getUuid(context));
//            Log.d(IkhpApplication.BASIC_TAG, "BOARD : " +  Build.BOARD);
//            Log.d(IkhpApplication.BASIC_TAG, "BOOTLOADER : " +  Build.BOOTLOADER);
//            Log.d(IkhpApplication.BASIC_TAG, "DISPLAY : " +  Build.DISPLAY);
//            Log.d(IkhpApplication.BASIC_TAG, "FINGERPRINT : " +  Build.FINGERPRINT);
//            Log.d(IkhpApplication.BASIC_TAG, "TAGS : " +  Build.TAGS);
//            Log.d(IkhpApplication.BASIC_TAG, "TYPE : " +  Build.TYPE);
//            Log.d(IkhpApplication.BASIC_TAG, "USER : " +  Build.USER);
//            for (int i=0; i < Build.SUPPORTED_ABIS.length; i++){
//                Log.d(IkhpApplication.BASIC_TAG, "SUPPORTED_ABIS : " +  Build.SUPPORTED_ABIS[i]);
//            }
//            for (int i=0; i < Build.SUPPORTED_32_BIT_ABIS.length; i++){
//                Log.d(IkhpApplication.BASIC_TAG, "SUPPORTED_32_BIT_ABIS : " +  Build.SUPPORTED_32_BIT_ABIS[i]);
//            }
//            for (int i=0; i < Build.SUPPORTED_64_BIT_ABIS.length; i++){
//                Log.d(IkhpApplication.BASIC_TAG, "SUPPORTED_64_BIT_ABIS : " +  Build.SUPPORTED_64_BIT_ABIS[i]);
//            }
//            Log.d(IkhpApplication.BASIC_TAG, "getRadioVersion : " +  Build.getRadioVersion());
        }catch (SecurityException e){
            e.printStackTrace();
            return null;
        }
        return info;
    }

    /**
     * 현재 기기의 휴대폰 번호 획득 메소드 <br />
     *
     * android.permission.READ_PHONE_STATE <br />
     * android.permission.READ_PHONE_NUMBERS <br />
     *
     * @author 곽성규
     * @param context
     * @return
     */
    public static String[] getPhoneNumber(Context context){
        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService(TELEPHONY_SERVICE);
        try {
            String[] result = new String[3];
            String phoneNumber = telephonyManager.getLine1Number();
            if(phoneNumber != null && !phoneNumber.equals("")){
                if(phoneNumber.startsWith("+82")){
                    phoneNumber = phoneNumber.replace("+82", "0");
                    if(phoneNumber.length() == 10){
                        result[0] = phoneNumber.substring(0, 3);
                        result[1] = phoneNumber.substring(3, 6);
                        result[2] = phoneNumber.substring(6, 10);
                        return result;
                    }else {
                        result[0] = phoneNumber.substring(0, 3);
                        result[1] = phoneNumber.substring(3, 7);
                        result[2] = phoneNumber.substring(7, 11);
                        return result;
                    }

                }
            }else{
                return new String[0];
            }
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return new String[0];
    }

    /**
     * 현재 기기의 저장된 연락처 획득 메소드 <br />
     * android.permission.READ_CONTACTS <br />
     *
     * @author 곽성규
     * @param context
     * @return
     */
    public static ArrayList<String> getContact(Context context){
        ArrayList<String> contacts = new ArrayList<String>();
        Cursor c = context.getApplicationContext().getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                null, null, null);
        while (c.moveToNext()) {
            String contactName = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phNumber = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contacts.add(contactName + ":" + phNumber);

        }
        c.close();
        return contacts;
    }

    /**
     * 현재 기기의 기본적인 카메라 실행 메소드 <br />
     * android.permission.CAMERA <br />
     *
     * @author 곽성규
     * @param activity
     * @param requestCode
     */
    public static String callCamera(Activity activity, int requestCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String mCurrentPhotoPath = "";
        if (takePictureIntent.resolveActivity(activity.getApplication().getPackageManager()) != null) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                File photoFile = null;
                try {
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    String imageFileName = "IMG_" + timeStamp + "_";

                    File storageDir = new File(
                            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
                            "Camera"
                    );

                    if(!storageDir.exists()){
                        storageDir.mkdirs();
                    }

                    photoFile = File.createTempFile(
                            imageFileName,  /* prefix */
                            ".jpg",   /* suffix */
                            storageDir      /* directory */
                    );

                }catch (IOException e){
                    e.printStackTrace();
                }

                if(photoFile != null){
                    Uri photoURI = FileProvider.getUriForFile(activity.getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider", photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    activity.startActivityForResult(takePictureIntent, requestCode);

                    mCurrentPhotoPath = "file:" + photoFile.getAbsolutePath();
                    return mCurrentPhotoPath;
                }
            }else {
                activity.startActivityForResult(takePictureIntent, requestCode);
            }
        }
        return mCurrentPhotoPath;
    }

    public static String callCamera(Fragment fragment, int requestCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String mCurrentPhotoPath = "";
        if (takePictureIntent.resolveActivity(fragment.getContext().getPackageManager()) != null) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                File photoFile = null;
                try {
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    String imageFileName = "IMG_" + timeStamp + "_";

                    File storageDir = new File(
                            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
                            "Camera"
                    );

                    if(!storageDir.exists()){
                        storageDir.mkdirs();
                    }

                    photoFile = File.createTempFile(
                            imageFileName,  /* prefix */
                            ".jpg",   /* suffix */
                            storageDir      /* directory */
                    );

                }catch (IOException e){
                    e.printStackTrace();
                }

                if(photoFile != null){
                    Uri photoURI = FileProvider.getUriForFile(fragment.getContext(), BuildConfig.APPLICATION_ID + ".provider", photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    fragment.startActivityForResult(takePictureIntent, requestCode);

                    mCurrentPhotoPath = "file:" + photoFile.getAbsolutePath();
                    return mCurrentPhotoPath;
                }
            }else {
                fragment.startActivityForResult(takePictureIntent, requestCode);
            }
        }
        return mCurrentPhotoPath;
    }

    public static void addGallery(Context context, String currentPhotoPath){
        Uri imageUri = Uri.parse(currentPhotoPath);
        MediaScannerConnection.scanFile(context.getApplicationContext(),
                new String[]{imageUri.getPath()},
                null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {

                    }
                });
    }


  /*  public static void addPicToGallery(Context context, String photoPath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(photoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }*/

    /**
     * 라이브러리를 이용한 갤러리 실행 및 이미지 획득 메소드 <br />
     *
     * implementation 'com.github.esafirm.android-image-picker:imagepicker:1.12.0' <br />
     *
     * android.permission.READ_EXTERNAL_STORAGE <br />
     * android.permission.WRITE_EXTERNAL_STORAGE <br />
     *
     * @author 곽성규
     * @param activity
     * @param selectCount 한번에 가져올 사진 개수 지정
     * @param folderMode 모드 지정
     * @param showCamera 카메라 기능 사용 여부
     */
    public static void callGallery(Activity activity, int selectCount, boolean folderMode, boolean showCamera){
        ImagePicker.create(activity)
                .returnMode(ReturnMode.NONE)
                .folderMode(folderMode)
                .toolbarFolderTitle("앨범")
                .toolbarImageTitle("")
                .limit(selectCount)
                .showCamera(showCamera)
                .start();
//        if(ImagePicker.shouldHandle(requestCode, resultCode, data)) {
//            RESGalleryList<Image> images = ImagePicker.getImages(data);
//            for (int i = 0; i < images.size(); i++) {
//                Glide.with(this).load(images.get(i).getPath()).into(img_profile);
//            }
//        }

    }
    public static void callGallery(Fragment fragment, int selectCount, boolean folderMode, boolean showCamera){
        ImagePicker.create(fragment)
                .returnMode(ReturnMode.NONE)
                .folderMode(folderMode)
                .toolbarFolderTitle("앨범")
                .toolbarImageTitle("")
                .limit(selectCount)
                .showCamera(showCamera)
                .start();
    }

    /**
     * 현재 디스플레이에 사용중인 상단 상태바의 크기(px)를
     * 획득하는 메소드
     *
     * @author 곽성규
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int height;
        Resources myResources = context.getApplicationContext().getResources();
        int idStatusBarHeight = myResources.getIdentifier(
                "status_bar_height", "dimen", "android");
        if (idStatusBarHeight > 0) {
            height = context.getApplicationContext().getResources().getDimensionPixelSize(idStatusBarHeight);
        }else{
            height = 0;
        }
        return height;
    }

    public static class BadgeType{
        public static final int PLUS = 1;
        public static final int MINUS = 2;
        public static final int RESET = 3;
    }
    /**
     * 현재 기기의 설치된 앱의 런처 액티비티에
     * 디스플레이되는 배지 카운트 컨트롤 메소드
     *
     * @author 곽성규
     * @param context
     * @param count 배지 카운트
     * @param badgeType 연산 결정
     */
    public static void setBadgeCount(Context context, int count, int badgeType) {
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences(IkhpApplication.PREF_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if(pref != null){
            int beforeCount = pref.getInt("data.badge", 0);
            if(badgeType > 0){
                Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
                intent.putExtra("badge_count_package_name", IkhpApplication.getPackageName(context));
                intent.putExtra("badge_count_class_name", IkhpApplication.getLaunchClassName(context));
                switch (badgeType){
                    case BadgeType.PLUS:
                        intent.putExtra("badge_count", beforeCount + count);
                        break;
                    case BadgeType.MINUS:
                        intent.putExtra("badge_count", beforeCount - count);
                        break;
                    case BadgeType.RESET:
                        intent.putExtra("badge_count", count);
                        break;
                }
                context.getApplicationContext().sendBroadcast(intent);
                editor.putInt("data.badge", beforeCount);
                editor.commit();
            }
        }
    }

    /**
     * 인터넷 연결 유무 상태를 확인 <br />
     *
     * uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" <br />
     * uses-permission android:name="android.permission.INTERNET" <br />
     *
     * @author 곽성규
     * @param context
     * @return
     */
    public static boolean isNetWorkEnable(Context context) {
        ConnectivityManager connect = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            Network[] activeNetWorks = connect.getAllNetworks();
            for(Network n : activeNetWorks){
                NetworkInfo networkInfo = connect.getNetworkInfo(n);
                if(networkInfo.isConnected()){
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public static int getNetworkType(Context context) {
        NetworkInfo info = ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE && info.isConnected()) {
                return ConnectivityManager.TYPE_MOBILE;
            }

            if (info.getType() == ConnectivityManager.TYPE_WIFI && info.isConnected()) {
                return ConnectivityManager.TYPE_WIFI;
            }
        }
        return -1;
    }

    /**
     * GPS ON/OFF 여부를 확인 <br />
     *
     * uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" <br />
     *                              or
     * uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" <br />
     *
     * @author 곽성규
     * @param context
     * @return
     */
    public static boolean getGpsState(Context context){
        LocationManager manager = (LocationManager) context.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        if(manager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
//            activity.startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 1000);
            return true;
        }
        return false;
    }

    /**
     * 네트워크 위치를 이용한 사용자의 위,경도 확인 <br />
     *
     * uses-permission android:name="android.permission.INTERNET" <br />
     * uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" <br />
     * uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" <br />
     *
     * @author 곽성규
     * @param context
     * @return lat = 위도
     *         lon = 경도
     */
    public static Map<String, Double> getNetWorkLocations(Context context) {
        Map<String, Double> result = new HashMap<>();
        try {
            LocationManager manager = (LocationManager) context.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
            Location netLocation = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(netLocation != null){
                result.put("lat", netLocation.getLatitude());
                result.put("lon", netLocation.getLongitude());
                return result;
            }else {
                return result;
            }
        }catch (SecurityException e){
            e.printStackTrace();
            return result;
        }
    }

    /**
     * 위,경도를 이용한 사용자의 주소값 확인
     * (인터넷 미 연결시 에러발생) <br />
     *
     * uses-permission android:name="android.permission.INTERNET" <br />
     *
     * @author 곽성규
     * @param context
     * @param lat 위도
     * @param lon 경도
     * @return String 주소
     */
    public static String getAddress(Context context, double lat, double lon) {
        try {
            Geocoder geocoder = new Geocoder(context.getApplicationContext());
            List<Address> addressList = geocoder.getFromLocation(lat, lon, 10);
            if(addressList != null && addressList.size() > 0){
                return addressList.get(0).getAddressLine(0);
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * 날씨 API 요청을 위한 요청 지역 파싱과
     *
     * 화면에 표시하기 위한 구,동,읍,리 등등 상세주소를 얻기 위한 메소드
     *
     * 대한민국에 한하여 국한. <br />
     *
     * Request Address = 서울, 경기, 경남, 충남, 등등 <br />
     * Display Address = 서귀포시 남원읍, 의정부시 녹양동, 서울특별시 중구 등등 <br />
     *
     * @param context
     * @param lat 위도
     * @param lon 경도
     * @return index 0 = Request Address <br />
     *         index 1 = Display Address <br />
     */
    public static String[] getRequestAndDisplayAddress(Context context, double lat, double lon){
        String address[] = null;
        try {
            Geocoder geocoder = new Geocoder(context.getApplicationContext());
            List<Address> addressList = geocoder.getFromLocation(lat, lon, 10);
            if(addressList != null && addressList.size() > 0){
               String[] tokens = addressList.get(0).getAddressLine(0).split(" ");

               if(tokens[0].equals("대한민국")){
                   String localKeyword = tokens[1];

                   if(localKeyword.contains("광역시") || localKeyword.contains("특별시") || localKeyword.contains("특별자치도")) {
                       String displayAddress;
                       String reqAddress;
                       if(localKeyword.contains("특별자치도")) {
                           displayAddress = tokens[2] + " " + tokens[3];
                       }else {
                           displayAddress = tokens[1] + " " + tokens[2];
                       }
                       reqAddress = localKeyword.replaceAll("광역시", "").replaceAll("특별시", "").replaceAll("특별자치도", "").trim();
                       address = new String[]{reqAddress, displayAddress};
                   }
                   else if(localKeyword.contains("남도") || localKeyword.contains("북도") || localKeyword.contains("도")) {
                       String displayAddress = tokens[2] + " " + tokens[3];
                       String reqAddress = localKeyword.replaceAll("도", "").trim();
                       if(reqAddress.length() > 2) {
                           reqAddress = reqAddress.substring(0, 1) + reqAddress.substring(2, 3);
                       }
                       address = new String[]{reqAddress, displayAddress};
                   }
               }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return address;
    }
}
