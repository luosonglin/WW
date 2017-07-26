package com.winwin.app;

import android.content.Context;

/**
 * Instrumentation Constant, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under Constant.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.winwin.app", appContext.getPackageName());
    }
}
