package com.example.c3ntry.sentientcloud;

import com.android.volley.toolbox.StringRequest;

/**
 * Created by c3ntyn3l on 6/7/2017.
 */

public class List_items
    {
        private String title;
        private String main;
        private String down;

        public List_items(String title, String main,String down)
        {
            this.title = title;
            this.main = main;
            this.down=down;
        }

        public String getTitle()
        {
            return title;
        }

        public String getMain()
        {
            return main;
        }

        public String getDown()
        {
            return down;
        }
    }
