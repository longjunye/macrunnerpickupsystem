package com.fairymo.macrunnerpickupsystem.network;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.util.Log;
import com.fairymo.macrunnerpickupsystem.BuildConfig;
import com.fairymo.macrunnerpickupsystem.CallingApplication;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.net.ssl.*;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.security.cert.CertificateException;

public class CallingRequestFactory {
	private static final String BASE_URL = "https://www.esteelauderpos.com";
	private static final String BASE_URL_DEBUG = "https://dev.esteelauderpos.com";
	private static final long TIMEOUT = 6;

	private static CallingService service = new Retrofit.Builder()
		.baseUrl(BuildConfig.DEBUG ? BASE_URL_DEBUG : BASE_URL)
		.addConverterFactory(
			GsonConverterFactory.create(new GsonBuilder().setLenient().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).serializeNulls().create()))
		.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
		.addConverterFactory(new FileRequestBodyConverterFactory())
		.client(okHttpClient())
		.build()
		.create(CallingService.class);

	public static CallingService getInstance() {
		return service;

	}

	@NonNull
	private static OkHttpClient okHttpClient() {
//		try {
//			CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
//			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//			keyStore.load(null);
//			InputStream cer = CallingApplication.getApp().getAssets().open("lurenqiuwang.cer");
//			keyStore.setCertificateEntry("1", certificateFactory.generateCertificate(cer));
//			try {
//				cer.close();
//			} catch (IOException e) {
//				Log.i("ylj", "cer open failed" + e.getLocalizedMessage());
//			}
//
//			TrustManagerFactory trustManagerFactory =
//				TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//
//			trustManagerFactory.init(keyStore);
//
//			SSLContext sslContext = SSLContext.getInstance("TLS");
//			sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
//			SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
//			return new OkHttpClient.Builder()
//				//Header
//				.addInterceptor(new Interceptor() {
//					@Override
//					public Response intercept(@NonNull Chain chain) throws IOException {
//						Request.Builder builder = chain.request().newBuilder();
//						builder
//							//							.addHeader("verifyCode", KingUtils.getVerifyCode(chain.request().url().toString()))
//							//							.addHeader("userToken", SharedPreferencesUtil.getString(KingAppLike.getApp(), Constant.REQUEST_TOKEN, ""))
//							.addHeader("fairymo", Utils.getUserAgent());
//
//						return chain.proceed(builder.build());
//					}
//				})
//				.sslSocketFactory(sslSocketFactory)
//				.hostnameVerifier(new HostnameVerifier() {
//					@Override
//					public boolean verify(String hostname, SSLSession session) {
//						return hostname.equals("127.0.0.1")
//							|| hostname.equals("localhost")
//							|| hostname.equals("dev.esteelauderpos.com");
//					}
//				})
//				.retryOnConnectionFailure(true)
//				.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//					@Override
//					public void log(@NonNull String message) {
//						Log.i(CallingApplication.getApp().getClass().getSimpleName(), message);
//					}
//				}).setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
//				.connectTimeout(TIMEOUT, TimeUnit.SECONDS)
//				.readTimeout(TIMEOUT, TimeUnit.SECONDS)
//				.build();
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
		try {
			// Create a trust manager that does not validate certificate chains
			final TrustManager[] trustAllCerts = new TrustManager[]{
				new X509TrustManager() {
					@SuppressLint("TrustAllX509TrustManager")
					@Override
					public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
					}

					@SuppressLint("TrustAllX509TrustManager")
					@Override
					public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
					}

					@Override
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return new java.security.cert.X509Certificate[]{};
					}
				}
			};

			// Install the all-trusting trust manager
			final SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

			// Create an ssl socket factory with our all-trusting manager
			final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			OkHttpClient.Builder builder = new OkHttpClient.Builder();
			builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
			builder.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
									@Override
									public void log(@NonNull String message) {
										Log.i(CallingApplication.getApp().getClass().getSimpleName(), message);
									}
								}).setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE));
			builder.hostnameVerifier(new HostnameVerifier() {
				@SuppressLint("BadHostnameVerifier")
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
			return builder.build();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static class FileRequestBodyConverterFactory extends Converter.Factory {
		@Override
		public Converter<File, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations,
			Retrofit retrofit) {
			return new FileRequestBodyConverter();
		}
	}

	private static class FileRequestBodyConverter implements Converter<File, RequestBody> {

		@Override
		public RequestBody convert(@NonNull File file) {
			return RequestBody.create(MediaType.parse("application/otcet-stream"), file);
		}
	}
}
