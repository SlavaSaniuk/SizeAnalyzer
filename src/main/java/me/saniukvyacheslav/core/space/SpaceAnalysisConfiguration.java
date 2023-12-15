package me.saniukvyacheslav.core.space;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpaceAnalysisConfiguration {

    private static SpaceAnalysisConfiguration INSTANCE; // Singleton instance;
    private SpaceAnalysisService spaceAnalysisService;

    /**
     * Get instance of this configuration class.
     * @return - singleton instance.
     */
    public static SpaceAnalysisConfiguration getInstance() {
        if (INSTANCE == null) INSTANCE = new SpaceAnalysisConfiguration();
        return INSTANCE;
    }

    /**
     * Get/create implementation of {@link SpaceAnalysisService} service.
     * @return - implementation of service.
     */
    public SpaceAnalysisService getSpaceAnalysisService() {
        if (this.spaceAnalysisService != null)
            return this.spaceAnalysisService;

        this.spaceAnalysisService = new SpaceAnalysisServiceImpl();
        return this.spaceAnalysisService;
    }

}
