
package com.example.admin_main;

        import java.util.HashMap;
        import java.util.Locale;

        import retrofit2.Call;
        import retrofit2.http.GET;
        import retrofit2.http.Path;

public interface RetrofitAPI {

//***********new kids chart*****************

    @GET("/kidschart/{period}")
    Call<double[]> kidsChart(@Path("period") int period);

// ***********new parents chart*****************

    @GET("/parentschart/{period}")
    Call<double[]> parentsChart(@Path("period") int period);

//***********activity in hour*****************

    @GET("/meetingrepo/{period}")
    Call<double[]>meetingsChart(@Path("period") int period);

//***********total per category*****************

    @GET("/getkidsnumincategoryperperiod/{period}")
    Call<HashMap<String, Integer>>getkidsNumInCategoryPerPeriod(@Path("period") int period);

//***********BAR CHART********************

    @GET("/barchartresults/{period}")
    Call<HashMap<Locale.Category, HashMap<Integer, Integer>>>barChartResults(@Path("period") int period);
}




