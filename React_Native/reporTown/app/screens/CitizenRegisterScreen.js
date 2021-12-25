import React from 'react';
import {ImageBackground, StyleSheet} from "react-native"

function CitizenRegisterScreen(props) {
    return (
      <ImageBackground
      style = {styles.background}
      source={require("../assets/background.jpg")}
      ></ImageBackground>
    );
}

const styles = StyleSheet.create({
    background:{
        flex:1
    }
})
export default CitizenRegisterScreen;