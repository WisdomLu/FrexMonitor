namespace java com.ocean.proxy.thrift.entity

// static attributes.
struct ProxyConfig {
	1: required string name,
	2: required string uri,
	// todo:
}

// dynamic attributes.
struct ProxyPolicy {
	1: required i32 weight,
	2: required bool trun_play,
}