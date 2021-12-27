import React from 'react';
import {ImageBackground, StyleSheet, View, Button, TouchableOpacity, Image,Text} from "react-native"

function PostScreen({navigation}) {
    return (
    
        <TouchableOpacity
            style={{
              marginTop:"35%",
              flex: 0.5,
              alignSelf: 'center',
              alignItems: 'center',
            }}
            onPress={() => navigation.navigate('CameraScreen')}>
             <Text>Take Photo</Text>
          </TouchableOpacity>
    
    );
}

const styles = StyleSheet.create({
    background:{
        flex:1
    }
})
export default PostScreen;