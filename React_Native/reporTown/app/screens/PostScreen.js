import React, {useState, useEffect} from 'react';
import {ImageBackground, StyleSheet,TextInput,Keyboard, View, Button, TouchableOpacity, Image,Text, SafeAreaView,StatusBar, Dimensions} from "react-native"
import SelectDropdown from "react-native-select-dropdown";
import { SearchBar } from 'react-native-elements';
import * as Location from 'expo-location';

function PostScreen({navigation}) {

    const [text, onChangeText] = React.useState("Useless Text");
    const [number, onChangeNumber] = React.useState(null);
    const [location, setLocation] = useState(null);
    var categories = [ "Water", "Road", "Traffic", "Garbage", "Crime", "Animal" ];
    const [errorMsg, setErrorMsg] = useState(null);

    const [search, setSearch] = useState("");

    const updateSearch = (search) => {
      console.log("hjashjs");
      setSearch(search);
    };

    const getLocation = () => {
        (async () => {
          let { status } = await Location.requestForegroundPermissionsAsync();
          if (status !== 'granted') {
            setErrorMsg('Permission to access location was denied');
            return;
          }
    
          let location = await Location.getCurrentPositionAsync({});
          setLocation(location);
        })();
        let text1 = 'Waiting..';
        if (errorMsg) {
          text1 = errorMsg;
        } else if (location) {
          text1 = JSON.stringify(location);
          console.log(text1);
        }
      }

    
    
     
    

    return (
        <SafeAreaView
          style={{
            width: "100%",
            height: "100%",
            backgroundColor: "#080f26",
            paddingTop:
              Platform.OS === "android" ? StatusBar.currentHeight * 1.5 : 0,
          }}
        >
        <View
            style={{
             flex: 0.7,
             flexDirection: "row",
              width: "30%",
              height: "30%",
              alignItems: "center",
              alignSelf: "center",
              justifyContent: "center",
            }}
          >
        <TouchableOpacity
              onPress={() => navigation.navigate('CameraScreen')}
              style={{
                height: "30%",
                minHeight: Dimensions.get("window").height / 10,
                padding: "3%",
                width: undefined,
                aspectRatio: 1,
                alignItems: "center",
              }}
            >
                 <Image
                source={require("../gui_components/camera.png")}
                style={{
                  width: "100%",
                  height: undefined,
                  aspectRatio: 1,
                  resizeMode: "contain",
                }}
              />
                 <Text
                 style={{
                     color:"white"
                 }}>Take Photo</Text>
            </TouchableOpacity>  
        </View>
        <TextInput
                  style={styles.textinput}
                  multiline={true}
                  numberOfLines={4}
                  
                  placeholder="Description"
                  underlineColorAndroid={"transparent"}
                  placeholderTextColor={"gray"}
                />

        <View
            style={{
              alignItems: "center",
              alignSelf: "center",
              justifyContent: "center",
            }}
          >
            <SelectDropdown
                data={categories}
                defaultButtonText='Category'
                onSelect={(selectedItem, index) => {
                    console.log(selectedItem, index)
                }}
                buttonTextAfterSelection={(selectedItem, index) => {
                    return selectedItem
                }}
                rowTextForSelection={(item, index) => {
                    return item
                }}
            />
        </View>

        <View
            style={{
                marginTop:"10%",
                width: "100%",
                
              alignItems: "center",
              alignSelf: "center",
              justifyContent: "center",
            }}
          >
        <TouchableOpacity style={styles.button}
              onPress={() => getLocation()}>
              <Text style={styles.buttontext}>Use My Current Location</Text>
        </TouchableOpacity>

        <TouchableOpacity style={styles.button}
              onPress={() => navigation.navigate('CitizenRegisterScreen')}>
              <Text style={styles.buttontext}>Set Location</Text>
        </TouchableOpacity>
        </View>

        <View
            style={{
                marginTop:"10%",
                width: "100%",
                
              alignItems: "center",
              alignSelf: "center",
              justifyContent: "center",
            }}
          >

        <TouchableOpacity style={styles.button}
              onPress={() => navigation.navigate('CitizenRegisterScreen')}>
              <Text style={styles.buttontext}>Post</Text>
        </TouchableOpacity>
        </View>




        </SafeAreaView>
    );
}

const styles = StyleSheet.create({
    background:{
        flex:1
    },
        textinput: {
            alignSelf: "stretch",
            height: "15%",
            marginLeft: 30,
            marginRight: 30,
            color: 'black',
            paddingLeft: 10,
            paddingRight: 10,
            marginBottom: 30,
            backgroundColor: "white",
            borderColor: "#f8f8f8",
            borderWidth: 1,
            borderRadius: 5,
          },

          button: {
            alignItems: "center",
            backgroundColor: "#cb7b23",
            width: "50%",
            marginBottom: "2%",
            borderRadius: 5,
            padding: 10,
          },
    
})
export default PostScreen;
