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

class LoginScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "",
      password: "",
      errorText: "",
    };

    this._isMounted = false;
  }

  async componentDidMount() {
    this._isMounted = true;
  }

  signInClicked = () => {
    const { username, password } = this.state;

    if (this.state.username == "a" && this.state.password == "a") {
      this.props.navigation.reset({
        index: 0,
        routes: [
          {
            name: "UserScreens",
            params: {
              username: this.state.username,
            },
          },
        ],
      });
    }

    if (this.state.username == "" && this.state.password == "") {
      this.props.navigation.reset({
        index: 0,
        routes: [
          {
            name: "InstScreens",
            params: {
              username: this.state.username,
            },
          },
        ],
      });
    }
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
              Login
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
                height: "70%",
                width: "70%",
                alignItems: "center",
                justifyContent: "center",
              }}
            >
              <TextInput
                style={{
                  borderBottomColor: "gray",
                  borderBottomWidth: 1,
                  width: "100%",
                  color: "white",

                  height:
                    Platform.OS === "ios"
                      ? Dimensions.get("window").height / 20
                      : undefined,
                  fontSize:
                    Platform.OS === "ios"
                      ? Dimensions.get("window").width / 25
                      : undefined,
                }}
                placeholder="Username"
                placeholderTextColor="white"
                autoCapitalize="none"
                textContentType="emailAddress"
                keyboardType="email-address"
                onChangeText={(value) =>
                  this._isMounted && this.setState({ username: value })
                }
              />
              <TextInput
                secureTextEntry={true}
                style={{
                  marginTop: "5%",
                  borderBottomColor: "gray",
                  borderBottomWidth: 1,
                  width: "100%",
                  color: "white",
                  height:
                    Platform.OS === "ios"
                      ? Dimensions.get("window").height / 20
                      : undefined,
                  fontSize:
                    Platform.OS === "ios"
                      ? Dimensions.get("window").width / 25
                      : undefined,
                }}
                placeholder="Password"
                placeholderTextColor="white"
                textContentType="password"
                onChangeText={(value) =>
                  this._isMounted && this.setState({ password: value })
                }
              />
            </View>
            <TouchableOpacity
              onPress={() => this.props.navigation.navigate("WelcomeScreen")}
              style={{
                width: "30%",
                alignItems: "center",
                height: "10%",
                justifyContent: "center",
                marginBottom: "40%",
              }}
            >
              <Text
                style={{
                  fontSize: Dimensions.get("window").width / 30,
                  textDecorationLine: "underline",
                  color: "#cb7b23",
                }}
              >
                Forgot Password?
              </Text>
            </TouchableOpacity>
            <View
              style={{
                marginBottom: "30%",
                width: "30%",
              }}
            >
              <Button
                onPress={this.signInClicked}
                title="Login"
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
            <View
              style={{
                width: "100%",
                alignItems: "center",
                flexDirection: "row",
                justifyContent: "center",
              }}
            >
              <Text
                style={{
                  fontSize: Dimensions.get("window").width / 25,
                  textAlignVertical: "center",
                  color: "white",
                }}
              >
                Dont'you have an account?
              </Text>
            </View>
            <TouchableOpacity
              onPress={() =>
                this.props.navigation.navigate("RegisterChooseScreen")
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
                  fontSize: Dimensions.get("window").width / 20,
                  textDecorationLine: "underline",
                  color: "#cb7b23",
                }}
              >
                Register
              </Text>
            </TouchableOpacity>
          </View>
        </SafeAreaView>
      </TouchableOpacity>
    );
  }
}

export default LoginScreen;
