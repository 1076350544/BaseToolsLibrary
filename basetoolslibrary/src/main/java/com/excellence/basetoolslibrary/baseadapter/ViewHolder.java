package com.excellence.basetoolslibrary.baseadapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by ZhangWei on 2016/6/1.
 */
public class ViewHolder
{
	private Context mContext = null;
	private View mConvertView = null;
	private SparseArray<View> mViews = null;

	public ViewHolder(Context context, ViewGroup parent, int layoutId)
	{
		mContext = context;
		mViews = new SparseArray<>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
		mConvertView.setTag(this);
	}

	public static ViewHolder getViewHolder(Context context, View convertView, ViewGroup parent, int layoutId)
	{
		if (convertView == null)
		{
			return new ViewHolder(context, parent, layoutId);
		}
		return (ViewHolder) convertView.getTag();
	}

	public View getConvertView()
	{
		return mConvertView;
	}

	/**
	 * ��ȡview�ؼ�
	 *
	 * @param viewId �ؼ���ԴId
     * @return view
     */
	public <T extends View> T getView(@IdRes int viewId)
	{
		View view = mViews.get(viewId);
		if (view == null)
		{
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}

	/**** ����Ϊ�������� *****/

	/**
	 * ��������
	 */

	/**
	 * �����ı�
	 *
	 * @param viewId �ؼ���ԴId
	 * @param strId �ַ�����ԴId
     * @return
     */
	public ViewHolder setText(@IdRes int viewId, @StringRes int strId)
	{
		TextView view = getView(viewId);
		view.setText(strId);
		return this;
	}

	/**
	 * �����ı�
	 *
	 * @param viewId �ؼ���ԴId
	 * @param text �ַ���
     * @return
     */
	public ViewHolder setText(@IdRes int viewId, String text)
	{
		TextView view = getView(viewId);
		view.setText(text);
		return this;
	}

	/**
	 * �����ı�
	 *
	 * @param viewId �ؼ���ԴId
	 * @param text �ַ���
     * @return
     */
	public ViewHolder setText(@IdRes int viewId, CharSequence text)
	{
		TextView view = getView(viewId);
		view.setText(text);
		return this;
	}

	/**
	 * ����������ɫ
	 *
	 * @param viewId �ؼ���ԴId
	 * @param textColor ��ɫ��Դ
     * @return
     */
	public ViewHolder setTextColor(@IdRes int viewId, @ColorInt int textColor)
	{
		TextView view = getView(viewId);
		view.setTextColor(textColor);
		return this;
	}

	/**
	 * ����������ɫ
	 *
	 * @param viewId �ؼ���ԴId
	 * @param textColorRes ��ɫ��ԴId
     * @return
     */
	public ViewHolder setTextColorRes(@IdRes int viewId, @ColorRes int textColorRes)
	{
		TextView view = getView(viewId);
		view.setTextColor(mContext.getResources().getColor(textColorRes));
		return this;
	}

	/**
	 * ����ͼƬ
	 *
	 * @param viewId �ؼ���ԴId
	 * @param resId ͼƬ��ԴId
     * @return
     */
	public ViewHolder setImageResource(@IdRes int viewId, @DrawableRes int resId)
	{
		ImageView view = getView(viewId);
		view.setImageResource(resId);
		return this;
	}

	/**
	 * ����ͼƬ
	 *
	 * @param viewId �ؼ���ԴId
	 * @param bitmap λͼ��Դ
     * @return
     */
	public ViewHolder setImageBitmap(@IdRes int viewId, Bitmap bitmap)
	{
		ImageView view = getView(viewId);
		view.setImageBitmap(bitmap);
		return this;
	}

	/**
	 * ����ͼƬ
	 *
	 * @param viewId �ؼ���ԴId
	 * @param drawable ͼƬ��Դ
     * @return
     */
	public ViewHolder setImageDrawable(@IdRes int viewId, @Nullable Drawable drawable)
	{
		ImageView view = getView(viewId);
		view.setImageDrawable(drawable);
		return this;
	}

	/**
	 * ���ñ�����ɫ
	 *
	 * @param viewId �ؼ���ԴId
	 * @param color ����ͼƬ��ɫ
     * @return
     */
	public ViewHolder setBackgroundColor(@IdRes int viewId, @ColorInt int color)
	{
		View view = getView(viewId);
		view.setBackgroundColor(color);
		return this;
	}

	/**
	 * ���ñ���ͼƬ
	 *
	 * @param viewId �ؼ���ԴId
	 * @param backgroundRes ����ͼƬ��ԴId
     * @return
     */
	public ViewHolder setBackgroundRes(@IdRes int viewId, @DrawableRes int backgroundRes)
	{
		View view = getView(viewId);
		view.setBackgroundResource(backgroundRes);
		return this;
	}

	/**
	 * ����͸����
	 *
	 * @param viewId �ؼ���ԴId
	 * @param value ͸����
     * @return
     */
	@SuppressLint("NewApi")
	public ViewHolder setAlpha(@IdRes int viewId, @FloatRange(from = 0.0, to = 1.0) float value)
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
		{
			getView(viewId).setAlpha(value);
		}
		else
		{
			// Pre-honeycomb hack to set Alpha value
			AlphaAnimation alpha = new AlphaAnimation(value, value);
			alpha.setDuration(0);
			alpha.setFillAfter(true);
			getView(viewId).startAnimation(alpha);
		}
		return this;
	}

	/**
	 * ���ÿؼ��Ƿ�ɼ�
	 *
	 * @param viewId �ؼ���ԴId
	 * @param visible �Ƿ�ɼ�
     * @return
     */
	public ViewHolder setVisible(@IdRes int viewId, boolean visible)
	{
		View view = getView(viewId);
		view.setVisibility(visible ? View.VISIBLE : View.GONE);
		return this;
	}

	/**
	 * �����ı�����
	 *
	 * @param viewId �ؼ���ԴId
	 * @return ������
     */
	public ViewHolder linkify(@IdRes int viewId)
	{
		TextView view = getView(viewId);
		Linkify.addLinks(view, Linkify.ALL);
		return this;
	}

	/**
	 * ��������������ʽ
	 *
	 * @param typeface ������ʽ
	 * @param viewIds �ؼ���ԴId
     * @return
     */
	public ViewHolder setTypeface(Typeface typeface, int... viewIds)
	{
		for (int viewId : viewIds)
		{
			TextView view = getView(viewId);
			view.setTypeface(typeface);
			view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
		}
		return this;
	}

	/**
	 * ���ý���������
	 *
	 * @param viewId �ؼ���ԴId
	 * @param progress ����
     * @return
     */
	public ViewHolder setProgress(@IdRes int viewId, int progress)
	{
		ProgressBar view = getView(viewId);
		view.setProgress(progress);
		return this;
	}

	/**
	 * ���ý��������Ⱥ����ֵ
	 *
	 * @param viewId �ؼ���ԴId
	 * @param progress ����
	 * @param max ������
     * @return
     */
	public ViewHolder setProgress(@IdRes int viewId, int progress, int max)
	{
		ProgressBar view = getView(viewId);
		view.setMax(max);
		view.setProgress(progress);
		return this;
	}

	/**
	 * ���ý��������ֵ
	 *
	 * @param viewId �ؼ���ԴId
	 * @param max ���ֵ
     * @return
     */
	public ViewHolder setMax(@IdRes int viewId, int max)
	{
		ProgressBar view = getView(viewId);
		view.setMax(max);
		return this;
	}

	/**
	 * ��������
	 *
	 * @param viewId �ؼ���ԴId
	 * @param rating ����
     * @return
     */
	public ViewHolder setRating(@IdRes int viewId, float rating)
	{
		RatingBar view = getView(viewId);
		view.setRating(rating);
		return this;
	}

	/**
	 * �������ֺ����ֵ
	 *
	 * @param viewId �ؼ���ԴId
	 * @param rating ����
	 * @param max ���ֵ
     * @return
     */
	public ViewHolder setRating(@IdRes int viewId, float rating, int max)
	{
		RatingBar view = getView(viewId);
		view.setMax(max);
		view.setRating(rating);
		return this;
	}

	/**
	 * ���ñ�ǩ
	 *
	 * @param viewId �ؼ���ԴId
	 * @param tag ��ǩ
     * @return
     */
	public ViewHolder setTag(@IdRes int viewId, Object tag)
	{
		View view = getView(viewId);
		view.setTag(tag);
		return this;
	}

	/**
	 * ���ñ�ǩ
	 *
	 * @param viewId �ؼ���ԴId
	 * @param key ��ֵ
	 * @param tag ��ǩ
     * @return
     */
	public ViewHolder setTag(@IdRes int viewId, int key, Object tag)
	{
		View view = getView(viewId);
		view.setTag(key, tag);
		return this;
	}

	/**
	 * ����check״̬
	 *
	 * @param viewId �ؼ���ԴId
	 * @param checked check״̬
     * @return
     */
	public ViewHolder setChecked(@IdRes int viewId, boolean checked)
	{
		Checkable view = (Checkable) getView(viewId);
		view.setChecked(checked);
		return this;
	}

	/**
	 * �����¼���
	 */

	/**
	 *
	 * @param viewId �ؼ���ԴId
	 * @param listener ����¼�
     * @return
     */
	public ViewHolder setOnClickListener(@IdRes int viewId, View.OnClickListener listener)
	{
		View view = getView(viewId);
		view.setOnClickListener(listener);
		return this;
	}

	/**
	 *
	 * @param viewId �ؼ���ԴId
	 * @param listener �����¼�
     * @return
     */
	public ViewHolder setOnTouchListener(@IdRes int viewId, View.OnTouchListener listener)
	{
		View view = getView(viewId);
		view.setOnTouchListener(listener);
		return this;
	}

	/**
	 *
	 * @param viewId �ؼ���ԴId
	 * @param listener �����¼�
     * @return
     */
	public ViewHolder setOnLongClickListener(@IdRes int viewId, View.OnLongClickListener listener)
	{
		View view = getView(viewId);
		view.setOnLongClickListener(listener);
		return this;
	}

}
