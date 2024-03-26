
package com.jcx.military.project.nas;

import jakarta.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(
        prefix = "disknas.temp"
)
public class NasInitialize {
    List<NasCache> cache;
    String name;

    public NasInitialize() {
    }

    @PostConstruct
    public void initNas() {
        this.nasTask();
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        executor.schedule(new Runnable() {
            public void run() {
                NasInitialize.this.nasTask();
            }
        }, 2L, TimeUnit.MINUTES);
    }

    private void nasTask() {
        Iterator var1 = this.cache.iterator();

        while(true) {
            while(var1.hasNext()) {
                NasCache cache = (NasCache)var1.next();
                String root = cache.getRoot();
                String filePath = root + "/" + this.name;
                boolean alive = NasCheck.checkNas(filePath);
                if (alive) {
                    int weight = cache.getWright() > 0 ? cache.getWright() : 1;

                    while(weight-- > 0) {
                        NasUtil.setRoot(root);
                    }
                } else {
                    NasUtil.rmValue(root);
                }
            }

            return;
        }
    }

    public List<NasCache> getCache() {
        return this.cache;
    }

    public void setCache(List<NasCache> cache) {
        this.cache = cache;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
