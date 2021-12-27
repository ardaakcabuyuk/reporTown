import React, { Component } from "react";
import {
  Text,
  TextInput,
  View,
  TouchableOpacity,
  Image,
  StatusBar,
  Button,
  Alert,
  SafeAreaView,
  Platform,
  Dimensions,
  Keyboard,
} from "react-native";
import CitizenRegisterScreen from "./CitizenRegisterScreen";

class RegisterChooseScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {

    };

    this._isMounted = false;
  }

  async componentDidMount() {
    this._isMounted = true;
  }

  citClicked = () => {
    this.props.navigation.navigate("CitizenRegisterScreen")
  };

  insClicked = () => {
    this.props.navigation.navigate("InstitutionRegisterScreen")
  };





  render() {
    return (
      <TouchableOpacity
        onPress={() => {
          Keyboard.dismiss();
        }}
        activeOpacity={5}
        style={{
          width: "100%",
          height: "100%",
          alignItems: "center",
          backgroundColor: "#080f26",
        }}
      >
        <SafeAreaView
          style={{
            width: "100%",
            height: "100%",
            paddingTop:
              Platform.OS === "android" ? StatusBar.currentHeight * 1.5 : 0,
            
          }}
        >
          <View style={{ height: "20%", alignItems: "center" }}>
            <Image
              source={require("../gui_components/reportown.png")}
              style={{ width: "60%", height: "100%", resizeMode: "contain" }}
            />
          </View>

          <View
            style={{
              flexDirection: "row",
              justifyContent: "space-evenly",
              height: "15%",
              alignItems: "center",
            }}
          >
            <Text
              style={{
                fontWeight: "bold",
                color: "white",
                fontSize: Dimensions.get("window").width / 12,
              }}
            >
              Register
            </Text>
          </View>

          
          <View
            style={{
              height: "40%",
              justifyContent: "space-evenly",
              alignItems: "center",
            }}
          >
            <View         
            style={{
                  marginBottom:"30%",
                  width:"50%",
                }}>
            
            <Button
            onPress={this.citClicked}
            title="As Citizen"
            color="#cb7b23"
            accessibilityLabel="Learn more about this purple button"
            />
            </View>
            
        

            <View         
            style={{
                  marginBottom:"0%",
                  width:"50%",
                }}>
            
            <Button
            onPress={this.insClicked}
            title="As Instıtution"
            color="#cb7b23"
            accessibilityLabel="Learn more about this purple button"
            />
            </View>
          </View>
          {/* <View
          style={{
            height: "30%",
            alignItems: "center",
            justifyContent: "center",
          }}
        >
          <TouchableOpacity
            style={{
              width: "60%",
              height: "20%",
              flexDirection: "row",
              alignItems: "center",
              padding: "1%",
            }}
          >
            <Image
              source={require("../gui_components/google_logo.png")}
              style={{
                width: "15%",
                height: undefined,
                aspectRatio: 1,
                resizeMode: "contain",
              }}
            />
            <Text style={{ marginLeft: "5%", width: "85%", fontSize: 18 }}>
              Google ile Giriş Yap
            </Text>
          </TouchableOpacity>
          <TouchableOpacity
            style={{
              width: "60%",
              height: "20%",
              marginTop: "5%",
              flexDirection: "row",
              alignItems: "center",
              padding: "1%",
            }}
          >
            <Image
              source={require("../gui_components/facebook_logo.png")}
              style={{
                width: "15%",
                height: undefined,
                aspectRatio: 1,
                resizeMode: "contain",
              }}
            />
            <Text style={{ marginLeft: "5%", width: "85%", fontSize: 18 }}>
              Facebook ile Giriş Yap
            </Text>
          </TouchableOpacity>
        </View> */}
          <View
            style={{
              width: "100%",
              height: "10%",
              alignItems: "center",
              justifyContent: "flex-start",
            }}
          >
         
            

            <TouchableOpacity
              onPress={() =>
                this.props.navigation.navigate("WelcomeScreen")
              }
              style={{
                width: "30%",
                alignItems: "center",
                height: "50%",
                justifyContent: "center",
              }}
            >
              <Text
                style={{
                  fontSize: Dimensions.get("window").width / 25,
                  textDecorationLine: "underline",
                }}
              >
                Şifremi Unuttum
              </Text>
            </TouchableOpacity>
          </View>
          
        </SafeAreaView>
      </TouchableOpacity>
    );
  }
}

export default RegisterChooseScreen;
