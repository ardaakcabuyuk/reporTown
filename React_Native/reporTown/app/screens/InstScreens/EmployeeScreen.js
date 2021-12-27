import React from 'react';
import {ImageBackground, StyleSheet} from "react-native"

function EmployeeScreen(props) {
    return (
      <ImageBackground
      style = {styles.background}
      source={require("../../assets/employee.png")}
      ></ImageBackground>
    );
}

const styles = StyleSheet.create({
    background:{
        flex:1
    }
})
export default EmployeeScreen;