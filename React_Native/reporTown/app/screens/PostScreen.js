import React from 'react';
import {ImageBackground, StyleSheet} from "react-native"

function PostScreen(props) {
    return (
      <ImageBackground
      style = {styles.background}
      source={require("../assets/post.jpg")}
      ></ImageBackground>
    );
}

const styles = StyleSheet.create({
    background:{
        flex:1
    }
})
export default PostScreen;