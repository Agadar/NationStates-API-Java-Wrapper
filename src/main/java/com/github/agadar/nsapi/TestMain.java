package com.github.agadar.nsapi;

import com.github.agadar.nsapi.domain.nation.Nation;
import com.github.agadar.nsapi.query.TelegramQuery;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author marti
 */
public class TestMain
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        NSAPI.setUserAgent("Agadar's Test Script (https://github.com/agadar)");
//        TelegramQuery q = NSAPI.telegram("e410c889", "15346505", "dffb17d06b0d", "agadar", "agadar", "agadar", "agadar", "agadar", "agadar");
//        long est = q.estimatedDuration();
//        System.out.println(String.format("%d min, %d sec",
//                TimeUnit.MILLISECONDS.toMinutes(est),
//                TimeUnit.MILLISECONDS.toSeconds(est)
//                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(est))
//        ));
//        q.execute();
    }

}
