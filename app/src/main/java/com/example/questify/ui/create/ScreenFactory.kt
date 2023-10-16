package com.example.questify.ui.create


class ScreenFactory(private val viewModel: QuestCreateViewModel) {
    enum class SCREENS {
        DEFINE,
        TARGET,
    }

    fun getScreen(type: SCREENS) = when(type) {
        SCREENS.DEFINE -> QuestDefineScreen(
            initialName = viewModel.quest.value.name,
            initialDescription = viewModel.quest.value.description,
            saveName = { viewModel.setName(it) },
            saveDescription = { viewModel.setDescription(it) },
        )
        SCREENS.TARGET -> QuestTargetScreen(
            initialTarget = viewModel.quest.value.targetReps,
            initialLimit = viewModel.quest.value.timeLimit,
            saveTarget = { viewModel.setTarget(it) },
            saveLimit = { viewModel.setTimeLimit(it) },
        )
    }
}