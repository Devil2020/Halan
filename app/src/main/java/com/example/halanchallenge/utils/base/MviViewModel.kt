package com.example.halanchallenge.utils.base

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

interface MviViewModel < IntentType : Intent , StateType : State> {

    fun processIntents (listOfIntents : Flow<IntentType>)

    @FlowPreview
    fun getStatus () : Flow<StateType>
    
}