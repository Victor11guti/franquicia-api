variable "org_id" {
  type        = string
  description = "MongoDB Atlas Organization ID"
}

variable "mongodb_public_key" {
  type        = string
  description = "MongoDB Atlas API Public Key"
}

variable "mongodb_private_key" {
  type        = string
  description = "MongoDB Atlas API Private Key"
  sensitive   = true
}

variable "mongodb_user_password" {
  type        = string
  description = "Password para el usuario Mongo Atlas"
  sensitive   = true
}
