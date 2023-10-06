package com.csidigital.management.service;

import com.csidigital.shared.dto.request.RecommendationRewardRequest;
import com.csidigital.shared.dto.response.RecommendationRewardResponse;

import java.util.List;

public interface RecommendationRewardService {
    RecommendationRewardResponse createRecommendationReward(RecommendationRewardRequest request);
    List<RecommendationRewardResponse> getAllRecommendationRewards();
    RecommendationRewardResponse getRecommendationRewardById(Long id);

    RecommendationRewardResponse updateRecommendationReward(RecommendationRewardRequest request, Long id);

    void deleteRecommendationReward(Long id);

}
