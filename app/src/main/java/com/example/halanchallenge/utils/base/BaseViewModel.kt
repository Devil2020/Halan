package com.example.halanchallenge.utils.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

abstract class BaseViewModel < IntentType : Intent , StateType : State> : ViewModel(){

    abstract fun processIntents (listOfIntents : Flow<IntentType>)

    abstract fun toState (intent: Intent) : Flow<StateType>

    @FlowPreview
    abstract fun getStatus () : Flow<StateType>
    
}