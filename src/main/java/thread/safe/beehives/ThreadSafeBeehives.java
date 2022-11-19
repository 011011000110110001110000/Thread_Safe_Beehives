package thread.safe.beehives;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadSafeBeehives implements ModInitializer {
    public static final String MOD_ID = "thread-safe-beehives";
    public static final String MOD_NAME = "Thread Safe Beehives";
    public static final String MOD_VERSION = "1.0.1";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);
    @Override
    public void onInitialize() {
        LOGGER.info(String.format("Initialized %s v.%s", MOD_NAME, MOD_VERSION));
    }
}
