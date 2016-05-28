package com.example.viewutils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class ViewUtils {
	
	public static void inject(final Activity activity) {
		
		Class< Activity> clazz = (Class<Activity>) activity.getClass();	
		injectField(activity, clazz);
		
		Method[] methods = clazz.getDeclaredMethods();
		for (final Method method : methods) {
			OnClick annotation = method.getAnnotation(OnClick.class);
			if(annotation!=null){
				int value = annotation.value();
				final View view = activity.findViewById(value);
				view.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						method.setAccessible(true);
						try {
							method.invoke(activity, view);
						} catch (Exception e) {
							e.printStackTrace();
						} 
					}
				});
			}
		}
	}

	private static void injectField(Activity activity, Class<Activity> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			ViewInject annotation = field.getAnnotation(ViewInject.class);
			if(annotation!=null){
				int value = annotation.value();
				field.setAccessible(true);
				View view = activity.findViewById(value);
				try {
					field.set(activity, view);
				} catch (Exception e) {
					e.printStackTrace();
				} 
				}
			}
	}
	}

