package com.galaxyy.lifelocke.entity.ai;

import net.minecraft.entity.ai.goal.Goal;

public class HideBlockGoal extends Goal {
    @Override
    public boolean canStart() {
        return false;
    }
}
