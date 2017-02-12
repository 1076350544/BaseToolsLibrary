package com.excellence.basetoolslibrary.utils;

/**
 * Created by ZhangWei on 2017/1/23.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * �������
 */
public class ShellUtils
{
	/**
	 * ִ������
	 *
	 * @param command
	 * @return
	 */
	public static CommandResult execProceeBuilderCommand(String... command)
	{
		Process process = null;
		StringBuilder msg = null;
		int resultCode = -1;
		try
		{
			process = new ProcessBuilder(command).redirectErrorStream(true).start();
			msg = getInputStream(process.getInputStream());
			resultCode = process.waitFor();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (process != null)
			{
				process.destroy();
			}
		}
		return new CommandResult(resultCode, msg == null ? null : msg.toString());
	}

	/**
	 * ִ������
	 *
	 * @param shell
	 * @return
	 */
	public static CommandResult execRuntimeCommand(String shell)
	{
		Process process = null;
		StringBuilder msg = null;
		int resultCode = -1;
		try
		{
			process = Runtime.getRuntime().exec(shell);
			msg = new StringBuilder();
			StringBuilder successMsg = getInputStream(process.getInputStream());
			StringBuilder errorMsg = getInputStream(process.getErrorStream());
			msg.append(successMsg).append(errorMsg);
			resultCode = process.waitFor();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (process != null)
			{
				process.destroy();
			}
		}
		return new CommandResult(resultCode, msg == null ? null : msg.toString());
	}

	/**
	 * ��Ϣ��
	 *
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	private static StringBuilder getInputStream(InputStream inputStream) throws IOException
	{
		BufferedReader stdin = new BufferedReader(new InputStreamReader(inputStream));
		StringBuilder result = new StringBuilder();
		String line = null;
		while ((line = stdin.readLine()) != null)
		{
			result.append(line);
		}
		stdin.close();
		return result;
	}

	/**
	 * ������
	 */
	public static class CommandResult
	{
		public int resultCode = -1;
		public String resultString = null;

		public CommandResult(String resultString)
		{
			this.resultString = resultString;
		}

		public CommandResult(int resultCode, String resultString)
		{
			this.resultCode = resultCode;
			this.resultString = resultString;
		}
	}
}
