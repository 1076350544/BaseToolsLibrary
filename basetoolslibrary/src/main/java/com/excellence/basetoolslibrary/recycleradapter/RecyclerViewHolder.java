package com.excellence.basetoolslibrary.recycleradapter;

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
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
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
 * Created by ZhangWei on 2016/12/20.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder
{
	private Context mContext = null;
	private View mConvertView = null;
	private SparseArray<View> mViews = null;

	public RecyclerViewHolder(Context context, View itemView)
	{
		super(itemView);
		mContext = context;
		mConvertView = itemView;
		mViews = new SparseArray<>();
	}

	public static RecyclerViewHolder getViewHolder(Context context, View view)
	{
		RecyclerViewHolder viewHolder = new RecyclerViewHolder(context, view);
		return viewHolder;
	}

	public static RecyclerViewHolder getViewHolder(Context context, ViewGroup parent, @LayoutRes int layoutId)
	{
		View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
		return getViewHolder(context, view);
	}

	public View getConvertView()
	{
		return mConvertView;
	}

	/**
	 *
	 * @param viewId �ؼ���ԴId
	 * @return ��ȡview����
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

	/**** ����Ϊ�������� ͬ com.excellence.basetoolslibrary.baseadapter.ViewHolder *****/

	/**
	 * ��������
	 */

	/**
	 *
	 * @param viewId �ؼ���ԴId
	 * @param strId �ַ�����ԴId
	 * @return
	 */
	public RecyclerViewHolder setText(@IdRes int viewId, @StringRes int strId)
	{
		TextView view = getView(viewId);
		view.setText(strId);
		return this;
	}

	/**
	 *
	 * @param viewId �ؼ���ԴId
	 * @param text �ַ���
	 * @return
	 */
	public RecyclerViewHolder setText(@IdRes int viewId, String text)
	{
		TextView view = getView(viewId);
		view.setText(text);
		return this;
	}

	/**
	 *
	 * @param viewId �ؼ���ԴId
	 * @param text �ַ���
	 * @return
	 */
	public RecyclerViewHolder setText(@IdRes int viewId, CharSequence text)
	{
		TextView view = getView(viewId);
		view.setText(text);
		return this;
	}

	/**
	 *
	 * @param viewId �ؼ���ԴId
	 * @param textColor ��ɫ��Դ
	 * @return
	 */
	public RecyclerViewHolder setTextColor(@IdRes int viewId, @ColorInt int textColor)
	{
		TextView view = getView(viewId);
		view.setTextColor(textColor);
		return this;
	}

	/**
	 *
	 * @param viewId �ؼ���ԴId
	 * @param textColorRes ��ɫ��ԴId
	 * @return
	 */
	public RecyclerViewHolder setTextColorRes(@IdRes int viewId, @ColorRes int textColorRes)
	{
		TextView view = getView(viewId);
		view.setTextColor(mContext.getResources().getColor(textColorRes));
		return this;
	}

	/**
	 *
	 * @param viewId �ؼ���ԴId
	 * @param resId ͼƬ��ԴId
	 * @return
	 */
	public RecyclerViewHolder setImageResource(@IdRes int viewId, @DrawableRes int resId)
	{
		ImageView view = getView(viewId);
		view.setImageResource(resId);
		return this;
	}

	/**
	 *
	 * @param viewId �ؼ���ԴId
	 * @param bitmap λͼ��Դ
	 * @return
	 */
	public RecyclerViewHolder setImageBitmap(@IdRes int viewId, Bitmap bitmap)
	{
		ImageView view = getView(viewId);
		view.setImageBitmap(bitmap);
		return this;
	}

	/**
	 *
	 * @param viewId �ؼ���ԴId
	 * @param drawable ͼƬ��Դ
	 * @return
	 */
	public RecyclerViewHolder setImageDrawable(@IdRes int viewId, Drawable drawable)
	{
		ImageView view = getView(viewId);
		view.setImageDrawable(drawable);
		return this;
	}

	/**
	 *
	 * @param viewId �ؼ���ԴId
	 * @param color ����ͼƬ��ɫ
	 * @return
	 */
	public RecyclerViewHolder setBackgroundColor(@IdRes int viewId, @ColorInt int color)
	{
		View view = getView(viewId);
		view.setBackgroundColor(color);
		return this;
	}

	/**
	 *
	 * @param viewId �ؼ���ԴId
	 * @param backgroundRes ����ͼƬ��ԴId
	 * @return
	 */
	public RecyclerViewHolder setBackgroundRes(@IdRes int viewId, @DrawableRes int backgroundRes)
	{
		View view = getView(viewId);
		view.setBackgroundResource(backgroundRes);
		return this;
	}

	/**
	 *
	 * @param viewId �ؼ���ԴId
	 * @param value ͸����
	 * @return
	 */
	@SuppressLint("NewApi")
	public RecyclerViewHolder setAlpha(@IdRes int viewId, @FloatRange(from = 0.0, to = 1.0) float value)
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
	 *
	 * @param viewId �ؼ���ԴId
	 * @param visible �Ƿ�ɼ�
	 * @return
	 */
	public RecyclerViewHolder setVisible(@IdRes int viewId, boolean visible)
	{
		View view = getView(viewId);
		view.setVisibility(visible ? View.VISIBLE : View.GONE);
		return this;
	}

	/**
	 *
	 * @param viewId �ؼ���ԴId
	 * @return ������
	 */
	public RecyclerViewHolder linkify(@IdRes int viewId)
	{
		TextView view = getView(viewId);
		Linkify.addLinks(view, Linkify.ALL);
		return this;
	}

	/**
	 *
	 * @param typeface ������ʽ
	 * @param viewIds �ؼ���ԴId
	 * @return
	 */
	public RecyclerViewHolder setTypeface(Typeface typeface, int... viewIds)
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
	 *
	 * @param viewId �ؼ���ԴId
	 * @param progress ����
	 * @return
	 */
	public RecyclerViewHolder setProgress(@IdRes int viewId, int progress)
	{
		ProgressBar view = getView(viewId);
		view.setProgress(progress);
		return this;
	}

	/**
	 *
	 * @param viewId �ؼ���ԴId
	 * @param progress ����
	 * @param max ������
	 * @return
	 */
	public RecyclerViewHolder setProgress(@IdRes int viewId, int progress, int max)
	{
		ProgressBar view = getView(viewId);
		view.setMax(max);
		view.setProgress(progress);
		return this;
	}

	/**
	 *
	 * @param viewId �ؼ���ԴId
	 * @param max ������
	 * @return
	 */
	public RecyclerViewHolder setMax(@IdRes int viewId, int max)
	{
		ProgressBar view = getView(viewId);
		view.setMax(max);
		return this;
	}

	/**
	 *
	 * @param viewId �ؼ���ԴId
	 * @param rating ����
	 * @return
	 */
	public RecyclerViewHolder setRating(@IdRes int viewId, float rating)
	{
		RatingBar view = getView(viewId);
		view.setRating(rating);
		return this;
	}

	/**
	 *
	 * @param viewId �ؼ���ԴId
	 * @param rating ����
	 * @param max ������
	 * @return
	 */
	public RecyclerViewHolder setRating(@IdRes int viewId, float rating, int max)
	{
		RatingBar view = getView(viewId);
		view.setMax(max);
		view.setRating(rating);
		return this;
	}

	/**
	 *
	 * @param viewId �ؼ���ԴId
	 * @param tag ��ǩ
	 * @return
	 */
	public RecyclerViewHolder setTag(@IdRes int viewId, Object tag)
	{
		View view = getView(viewId);
		view.setTag(tag);
		return this;
	}

	/**
	 *
	 * @param viewId �ؼ���ԴId
	 * @param key ��ֵ
	 * @param tag ��ǩ
	 * @return
	 */
	public RecyclerViewHolder setTag(@IdRes int viewId, int key, Object tag)
	{
		View view = getView(viewId);
		view.setTag(key, tag);
		return this;
	}

	/**
	 *
	 * @param viewId �ؼ���ԴId
	 * @param checked check״̬
	 * @return
	 */
	public RecyclerViewHolder setChecked(@IdRes int viewId, boolean checked)
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
	public RecyclerViewHolder setOnClickListener(@IdRes int viewId, View.OnClickListener listener)
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
	public RecyclerViewHolder setOnTouchListener(@IdRes int viewId, View.OnTouchListener listener)
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
	public RecyclerViewHolder setOnLongClickListener(@IdRes int viewId, View.OnLongClickListener listener)
	{
		View view = getView(viewId);
		view.setOnLongClickListener(listener);
		return this;
	}

}
