import React from 'react';
import {ImageBackground, StyleSheet, Text, View} from "react-native"

function FeedScreen(props) {
    return (
        <View style={{
            flex: 1, 
            alignItems: 'center',
            justifyContent: 'center', 
            backgroundColor: 'blue'
        }}>
            <Text style={{backgroundColor: 'red'}}>
                This is feed
            </Text>
        </View>
    );
}

const styles = StyleSheet.create({
    background:{
        flex:1
    }
})
export default FeedScreen;