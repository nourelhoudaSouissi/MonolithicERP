package com.csidigital.shared.dto.request;

import com.csidigital.shared.enumeration.RewardType;
import lombok.Data;

@Data
public class RecommendationRewardRequest {

    private double amount;
    private RewardType rewardType;
}
