package com.example.halanchallenge.utils.base

import com.example.halanchallenge.ui.entities.Intent
import com.example.halanchallenge.ui.entities.State
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

interface MviViewModel < IntentType : Intent , StateType : State> {

    fun processIntents (listOfIntents : Flow<IntentType>)

    @FlowPreview
    fun getStatus () : Flow<StateType>
    
}