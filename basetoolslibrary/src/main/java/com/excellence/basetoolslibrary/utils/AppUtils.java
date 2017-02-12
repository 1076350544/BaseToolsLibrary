package com.excellence.basetoolslibrary.utils;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

/**
 * Created by ZhangWei on 2016/12/19.
 */

/**
 * Ӧ�����
 */
public class AppUtils
{
	/**
	 * ��ȡ��װ������Ӧ��
	 *
	 * @param context
	 * @return
	 */
	public static List<ResolveInfo> getAllInstalledApps(Context context)
	{
		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		PackageManager packageManager = context.getPackageManager();
		List<ResolveInfo> apps = packageManager.queryIntentActivities(mainIntent, 0);
		// ��������
		Collections.sort(apps, new ResolveInfo.DisplayNameComparator(packageManager));
		return apps;
	}

	/**
	 * ��ȡ��װ��ϵͳӦ��
	 *
	 * @param context
	 * @return
	 */
	public static List<ResolveInfo> getSystemInstalledApps(Context context)
	{
		List<ResolveInfo> allApps = getAllInstalledApps(context);
		List<ResolveInfo> systemInstalledApps = new ArrayList<>();
		for (ResolveInfo resolveInfo : allApps)
		{
			if ((resolveInfo.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0)
			{
				systemInstalledApps.add(resolveInfo);
			}
		}
		return systemInstalledApps;
	}

	/**
	 * ��ȡ��װ�ĵ�����Ӧ��
	 *
	 * @param context
	 * @return
	 */
	public static List<ResolveInfo> getUserInstalledApps(Context context)
	{
		List<ResolveInfo> allApps = getAllInstalledApps(context);
		List<ResolveInfo> userInstalledApps = new ArrayList<>();
		for (ResolveInfo resolveInfo : allApps)
		{
			if ((resolveInfo.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0 || (resolveInfo.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0)
			{
				userInstalledApps.add(resolveInfo);
			}
		}
		return userInstalledApps;
	}

	/**
	 * ��ȡĳӦ�õ�����Ȩ��
	 *
	 * @param context
	 * @param packageName ĳӦ�ð���
	 * @return
	 */
	public static List<String> getPermissionList(Context context, String packageName)
	{
		List<String> permissionList = new ArrayList<>();
		try
		{
			PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
			if (packageInfo != null && packageInfo.requestedPermissions != null)
				permissionList.addAll(Arrays.asList(packageInfo.requestedPermissions));
		}
		catch (PackageManager.NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return permissionList;
	}

	/**
	 * ��ȡ��ǰӦ�õ�����Ȩ��
	 *
	 * @param context
	 * @return
	 */
	public static List<String> getPermissionList(Context context)
	{
		return getPermissionList(context, context.getPackageName());
	}

	/**
	 * ���ĳӦ���Ƿ���ĳȨ��
	 *
	 * @param context
	 * @param unCheckedPermission ������Ȩ��
	 * @param packageName ĳӦ�ð���
	 * @return
	 */
	public static boolean checkPermission(Context context, String unCheckedPermission, String packageName)
	{
		return PackageManager.PERMISSION_GRANTED == context.getPackageManager().checkPermission(unCheckedPermission, packageName);
	}

	/**
	 * ��⵱ǰӦ���Ƿ���ĳȨ��
	 *
	 * @param context
	 * @param unCheckedPermission ������Ȩ��
	 * @return
	 */
	public static boolean checkPermission(Context context, String unCheckedPermission)
	{
		return checkPermission(context, unCheckedPermission, context.getPackageName());
	}

	/**
	 * ��ȡ��ǰӦ�ð汾��
	 *
	 * @param context
	 * @return
	 */
	public static String getAppVersionName(Context context)
	{
		PackageManager manager = context.getPackageManager();
		String versionName = "";
		try
		{
			PackageInfo packageInfo = manager.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
			versionName = packageInfo.versionName;
		}
		catch (PackageManager.NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return versionName;
	}

	/**
	 * ��ȡ��ǰӦ�ð汾��
	 *
	 * @param context
	 * @return
	 */
	public static int getAppVersionCode(Context context)
	{
		PackageManager manager = context.getPackageManager();
		int versionCode = 0;
		try
		{
			PackageInfo packageInfo = manager.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
			versionCode = packageInfo.versionCode;
		}
		catch (PackageManager.NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return versionCode;
	}

	/**
	 * ��ȡ��ǰӦ�ô�С
	 *
	 * @param context
	 * @return
	 */
	public static String getAppSize(Context context)
	{
		PackageManager manager = context.getPackageManager();
		String size = "";
		try
		{
			PackageInfo packageInfo = manager.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
			size = FileUtils.formatFileSize((new File(packageInfo.applicationInfo.publicSourceDir).length()));
		}
		catch (PackageManager.NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return size;
	}

	/**
	 * ��ȡ��ǰӦ�ð�װʱ��
	 *
	 * @param context
	 * @return
	 */
	public static String getAppTime(Context context)
	{
		PackageManager manager = context.getPackageManager();
		String time = "";
		try
		{
			PackageInfo packageInfo = manager.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
			time = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(new File(packageInfo.applicationInfo.sourceDir).lastModified()));
		}
		catch (PackageManager.NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * ��ȡApp·��
	 *
	 * @param context
	 * @return
	 */
	public static String getAppPath(Context context)
	{
		try
		{
			PackageManager pm = context.getPackageManager();
			PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
			return packageInfo == null ? null : packageInfo.applicationInfo.sourceDir;
		}
		catch (PackageManager.NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��ȡapk�ļ���ǩ��
	 * ��ҪȨ�� {@link android.Manifest.permission.READ_EXTERNAL_STORAGE}
	 *
	 * @param apkPath apk�ļ�·��
	 * @return ֤��MD5ֵ:32λ16���� ��:D17A70403EB7CD52181004C847180287
	 */
	@Nullable
	public static String getAPKFileSignature(String apkPath)
	{
		String signatureMD5 = null;
		try
		{
			String PATH_PackageParser = "android.content.pm.PackageParser";
			Class pkgParserCls = Class.forName(PATH_PackageParser);
			Class[] typeArgs = new Class[1];
			typeArgs[0] = String.class;
			Constructor pkgParserCt = pkgParserCls.getConstructor(typeArgs);
			Object[] valueArgs = new Object[1];
			valueArgs[0] = apkPath;
			Object pkgParser = pkgParserCt.newInstance(valueArgs);

			DisplayMetrics metrics = new DisplayMetrics();
			metrics.setToDefaults();

			typeArgs = new Class[4];
			typeArgs[0] = File.class;
			typeArgs[1] = String.class;
			typeArgs[2] = DisplayMetrics.class;
			typeArgs[3] = Integer.TYPE;
			Method pkgParser_parsePackageMtd = pkgParserCls.getDeclaredMethod("parsePackage", typeArgs);
			valueArgs = new Object[4];
			valueArgs[0] = new File(apkPath);
			valueArgs[1] = apkPath;
			valueArgs[2] = metrics;
			valueArgs[3] = PackageManager.GET_SIGNATURES;
			Object pkgParserPkg = pkgParser_parsePackageMtd.invoke(pkgParser, valueArgs);

			typeArgs = new Class[2];
			typeArgs[0] = pkgParserPkg.getClass();
			typeArgs[1] = Integer.TYPE;
			Method pkgParser_collectCertificatesMtd = pkgParserCls.getDeclaredMethod("collectCertificates", typeArgs);
			valueArgs = new Object[2];
			valueArgs[0] = pkgParserPkg;
			valueArgs[1] = PackageManager.GET_SIGNATURES;
			pkgParser_collectCertificatesMtd.invoke(pkgParser, valueArgs);

			Field packageInfoFld = pkgParserPkg.getClass().getDeclaredField("mSignatures");
			Signature[] signatures = (Signature[]) packageInfoFld.get(pkgParserPkg);
			if (signatures.length > 0)
				signatureMD5 = getSignatureMD5(signatures[0]);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return signatureMD5;
	}

	/**
	 * ��ȡĳ��װӦ�õ�ǩ��
	 *
	 * @param context
	 * @param packageName
	 * @return ֤��MD5ֵ:32λ16���� ��:D17A70403EB7CD52181004C847180287
	 */
	@Nullable
	public static String getPackageSignature(Context context, String packageName)
	{
		String signatureMD5 = null;
		try
		{
			PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
			if (packageInfo.signatures.length > 0)
			{
				signatureMD5 = getSignatureMD5(packageInfo.signatures[0]);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return signatureMD5;
	}

	/**
	 * ��ȡ��ǰӦ�õ�ǩ��
	 *
	 * @param context
	 * @return ֤��MD5ֵ:32λ16���� ��:D17A70403EB7CD52181004C847180287
	 */
	@Nullable
	public static String getPackageSignature(Context context)
	{
		return getPackageSignature(context, context.getPackageName());
	}

	/**
	 * MD5ֵ
	 *
	 * @param signature
	 * @return
	 * @throws Exception
	 */
	private static String getSignatureMD5(Signature signature) throws Exception
	{
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] digest = md5.digest(signature.toByteArray());
		return ConvertUtils.bytesToHexString(digest);
	}

	/**
	 * �ж�Ӧ���Ƿ�װ
	 *
	 * @param context
	 * @param packageName
	 * @return
	 */
	public static PackageInfo isAppInstalled(Context context, String packageName)
	{
		try
		{
			return context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
		}
		catch (PackageManager.NameNotFoundException e)
		{
			return null;
		}
	}

	/**
	 * �ж�App�Ƿ���Debug�汾
	 *
	 * @param context
	 * @return
	 */
	public static boolean isAppDebug(Context context)
	{
		try
		{
			PackageManager pm = context.getPackageManager();
			ApplicationInfo info = pm.getApplicationInfo(context.getPackageName(), 0);
			return info != null && (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
		}
		catch (PackageManager.NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
