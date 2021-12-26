import React from 'react';
import {ImageBackground, StyleSheet} from "react-native"

function FeedScreen(props) {
    return (
      <ImageBackground
      style = {styles.background}
      source={require("../assets/feed.png")}
      ></ImageBackground>
    );
}

const styles = StyleSheet.create({
    background:{
        flex:1
    }
})
export default FeedScreen;