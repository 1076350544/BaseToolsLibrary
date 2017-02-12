package com.excellence.basetoolslibrary.utils;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by ZhangWei on 2017/1/24.
 */

/**
 * Handler���
 */
public class HandlerUtils
{
	public static class HandlerHolder extends Handler
	{
		WeakReference<OnReceiveMessageListener> mListenerWeakReference;

		/**
		 * ʹ�ñض����Ƽ���Activity����Activity�ڲ���������ʵ�ָýӿڣ���Ҫʹ�������࣬���ܻᱻGC
		 *
		 * @param listener
		 *            �յ���Ϣ�ص��ӿ�
		 */
		public HandlerHolder(OnReceiveMessageListener listener)
		{
			mListenerWeakReference = new WeakReference<>(listener);
		}

		@Override
		public void handleMessage(Message msg)
		{
			if (mListenerWeakReference != null && mListenerWeakReference.get() != null)
			{
				mListenerWeakReference.get().handlerMessage(msg);
			}
		}
	}

	/**
	 * �յ���Ϣ�ص��ӿ�
	 */
	public interface OnReceiveMessageListener
	{
		void handlerMessage(Message msg);
	}
}
